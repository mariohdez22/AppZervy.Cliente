package com.example.appzervycliente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    val onClose = { this.finishAffinity() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            AppZervyClienteTheme {
                navController = rememberNavController()
                SetupNavGraph(navController, onClose)
            }
        }
    }
}