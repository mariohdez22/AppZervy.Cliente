package com.example.appzervycliente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel
import com.example.appzervycliente.Views.ClientesTest.ClientesApp
import com.example.appzervycliente.Views.ClientesTest.MostrarClientes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppZervyClienteTheme {
                //val viewModel: ClientesViewModel = viewModel()
                //MostrarClientes(viewModel)
                ClientesApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppZervyClienteTheme {

    }
}