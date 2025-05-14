package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale


@Composable
fun UserTypeScreen(navController: NavController) {
    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1vi5GSfIMcaz6kYDdW2v8g0vOZDwuZ6ex"

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo adaptada
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop, // <-- Este es el cambio importante
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { navController.navigate("next_screen") }) {
                Text(text = "Comenzar")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}