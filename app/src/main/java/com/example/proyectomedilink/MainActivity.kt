package com.example.proyectomedilink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectomedilink.Navigation.AppNavHost
import com.example.proyectomedilink.repository.CitaMedicaRepository
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppNavHost(navController = navController)
            //MedicoScreen()

        }
    }

}


