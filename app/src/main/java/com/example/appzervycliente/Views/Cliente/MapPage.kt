package com.example.appzervycliente.Views.Cliente

import android.Manifest
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import kotlinx.coroutines.launch
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceMapsScreen(navController: NavHostController) {
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarw(navController = navController)
        },
        sheetContent = {
            BottomSheetContent()
        },
        sheetPeekHeight = 58.dp
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Sección "Socio en camino"
            CaminoSection()

            // Mapa con la ruta real y animación
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                AnimatedRouteMapWithClientLocation()
            }
        }
    }
}

@Composable
fun AnimatedRouteMapWithClientLocation() {
    val context = LocalContext.current
    val mapView = rememberMapViewWithLifecycle(context)
    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }

    // Coordenadas del colaborador y cliente
    val collaboratorLocation = LatLng(37.7749, -122.4194) // Coordenadas del socio
    var clientLocation by remember { mutableStateOf<LatLng?>(null) }

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    // Puntos de la ruta
    var routePoints by remember { mutableStateOf<List<LatLng>>(emptyList()) }

    // Referencia a la polilínea
    var polyline: Polyline? by remember { mutableStateOf(null) }

    // Animación de color para la polilínea
    val infiniteTransition = rememberInfiniteTransition()
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Blue,
        targetValue = Color.Cyan,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Obtener ubicación del cliente
    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val location = fusedLocationClient.lastLocation.await()
            clientLocation = location?.let {
                LatLng(it.latitude, it.longitude)
            } ?: LatLng(37.7790, -122.3890) // Coordenadas predeterminadas
        } else {
            // Solicitar permisos al usuario (si es necesario)
        }
    }

    // Obtener la ruta real desde la Directions API
    LaunchedEffect(clientLocation) {
        clientLocation?.let { clientLatLng ->
            val origin = "${collaboratorLocation.latitude},${collaboratorLocation.longitude}"
            val destination = "${clientLatLng.latitude},${clientLatLng.longitude}"
            val apiKey = context.getString(R.string.google_maps_key) // Asegúrate de tener la clave en strings.xml

            val urlStr = "https://maps.googleapis.com/maps/api/directions/json?" +
                    "origin=$origin&destination=$destination&key=$apiKey"

            withContext(Dispatchers.IO) {
                try {
                    val url = URL(urlStr)
                    val conn = url.openConnection() as HttpURLConnection
                    conn.requestMethod = "GET"
                    conn.connect()

                    val inputStream = conn.inputStream
                    val response = inputStream.bufferedReader().use { it.readText() }

                    val jsonResponse = JSONObject(response)
                    val routesArray = jsonResponse.getJSONArray("routes")
                    if (routesArray.length() > 0) {
                        val route = routesArray.getJSONObject(0)
                        val overviewPolyline = route.getJSONObject("overview_polyline")
                        val encodedPoints = overviewPolyline.getString("points")
                        val decodedPoints = decodePoly(encodedPoints)
                        routePoints = decodedPoints
                    } else {
                        Log.e("DirectionsAPI", "No se encontraron rutas")
                    }
                } catch (e: Exception) {
                    Log.e("DirectionsAPI", "Error al obtener direcciones: ${e.message}")
                }
            }
        }
    }

    // Actualizar el mapa cuando cambien googleMap o routePoints
    LaunchedEffect(googleMap, routePoints) {
        googleMap?.let { map ->
            map.uiSettings.isZoomControlsEnabled = true

            // Limpiar el mapa
            map.clear()
            polyline = null

            // Añadir marcador del colaborador (socio)
            map.addMarker(
                MarkerOptions()
                    .position(collaboratorLocation)
                    .title("Colaborador")
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            resizeMarker(context, R.drawable.avatar3dimage, 80, 80)
                        )
                    )
            )

            // Añadir marcador del cliente
            clientLocation?.let { clientLatLng ->
                map.addMarker(
                    MarkerOptions()
                        .position(clientLatLng)
                        .title("Cliente")
                        .icon(
                            BitmapDescriptorFactory.fromBitmap(
                                resizeMarker(context, R.drawable.union, 80, 80)
                            )
                        )
                )

                // Dibujar la ruta real
                if (routePoints.isNotEmpty()) {
                    val polylineOptions = PolylineOptions()
                        .addAll(routePoints)
                        .color(animatedColor.toArgb()) // Usar el color animado
                        .width(8f)
                    polyline = map.addPolyline(polylineOptions)

                    // Ajustar la cámara para mostrar toda la ruta
                    val boundsBuilder = LatLngBounds.Builder()
                    routePoints.forEach { boundsBuilder.include(it) }
                    val bounds = boundsBuilder.build()
                    map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
                }
            }
        }
    }

    // Actualizar el color de la polilínea para la animación
    LaunchedEffect(animatedColor) {
        polyline?.color = animatedColor.toArgb()
    }

    // Inicializar el mapa
    LaunchedEffect(Unit) {
        mapView.getMapAsync { map ->
            googleMap = map
        }
    }

    // Mostrar el MapView
    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize(),
        update = { }
    )
}

// Función para decodificar la polilínea
fun decodePoly(encoded: String): List<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dlat

        shift = 0
        result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dlng

        val p = LatLng(
            lat.toDouble() / 1E5,
            lng.toDouble() / 1E5
        )
        poly.add(p)
    }

    return poly
}

// Función para ajustar el MapView al ciclo de vida de Compose
@Composable
fun rememberMapViewWithLifecycle(context: Context): MapView {
    val mapView = remember { MapView(context) }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(null)
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
    return mapView
}

// Función para redimensionar los marcadores
fun resizeMarker(context: Context, resourceId: Int, width: Int, height: Int): Bitmap {
    val bitmap = BitmapFactory.decodeResource(context.resources, resourceId)
    return Bitmap.createScaledBitmap(bitmap, width, height, false)
}

@Composable
fun CaminoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Socio en camino",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        // Subtítulo
        Text(
            text = "El socio se encuentra cerca de la residencia",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Hora y progreso
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_access_time_24),
                contentDescription = "Clock Icon",
                modifier = Modifier.size(24.dp), // Ícono más grande
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "02:00 - 03:40",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp // Texto más grande
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Reemplaza la barra de progreso con la versión animada
        AnimatedProgressBar()
    }
}

@Composable
fun AnimatedProgressSegment(active: Boolean, colors: List<Color>, fraction: Float) {
    // Animación de opacidad para cada segmento
    val animationAlpha by animateFloatAsState(
        targetValue = if (active) 1f else 0.3f, // Ajusta opacidad para mostrar actividad
        animationSpec = tween(durationMillis = 500) // Duración de la animación
    )

    // Segmento individual
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction) // Ocupa una fracción específica del ancho
            .height(16.dp) // Aumenta la altura de los segmentos
            .clip(RoundedCornerShape(8.dp)) // Bordes redondeados más grandes
            .background(
                brush = Brush.horizontalGradient(colors),
                alpha = animationAlpha // Aplica la animación de opacidad
            )
    )
}

@Composable
fun AnimatedProgressBar() {
    var phase by remember { mutableStateOf(1) }
    val scope = rememberCoroutineScope()

    // Lógica de cambio de fase
    LaunchedEffect(Unit) {
        scope.launch {
            while (true) {
                phase = (phase % 3) + 1
                kotlinx.coroutines.delay(800) // Cambia de fase cada 0.8 segundos
            }
        }
    }

    // Filas para los segmentos
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), // Ajusta el padding si es necesario
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaciado entre segmentos
    ) {
        // Primer segmento
        AnimatedProgressSegment(
            active = phase >= 1,
            colors = listOf(Color(0xFF7E57C2), Color(0xFFB39DDB)), // Degradado 1
            fraction = 0.31f // Cada segmento ocupa 30% del ancho total
        )
        // Segundo segmento
        AnimatedProgressSegment(
            active = phase >= 2,
            colors = listOf(Color(0xFFB39DDB), Color(0xFFD1C4E9)), // Degradado 2
            fraction = 0.48f
        )
        // Tercer segmento
        AnimatedProgressSegment(
            active = phase >= 3,
            colors = listOf(Color(0xFFD1C4E9), Color(0xFFEDE7F6)), // Degradado 3
            fraction = 1f
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarw(navController: NavHostController) {
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Acción de configuración */ }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    )
}


@Composable
fun MapSection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray) // Esto simula el mapa
    ) {
        Text(
            text = "Mapa aquí",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Detalles del servicio",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Instalación Eléctrica",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo, sed quia consequuntur magni dolores, sed quia consequuntur magni. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailRow("Formato", "Varios Días")
        DetailRow("Nueva Duración", "4 Días")
        DetailRow("Nuevo Precio", "$350 USD")
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMapPage() {
    ServiceMapsScreen(navController = rememberNavController())
}
