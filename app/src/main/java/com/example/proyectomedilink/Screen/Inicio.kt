package com.example.proyectomedilink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun NextScreen(navController: NavController) {
    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1RMBM39XNfqopLbn5Nfasf6N1O4vp9sUB"

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAA000000)) // oscurece el fondo
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Elige una opción",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OptionIcon(
                    imageUrl = "https://drive.google.com/uc?export=download&id=1b-I3tLWD9q9VTREbc31F8E-tzQGswY63",
                    label = "Gestión de Médicos",
                    onClick = { navController.navigate("medico_screen") }
                )
                OptionIcon(
                    imageUrl = "https://drive.google.com/uc?export=download&id=1Wz9RAEBzmumS7uHjiQlnvbrzauiQl7Yx",
                    label = "Gestión de Pacientes",
                    onClick = { navController.navigate("paciente_screen") }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OptionIcon(
                    imageUrl = "https://drive.google.com/uc?export=download&id=1GHqgkPUQwY_ZRPsDtZQr9BwT_fMKxRKD",
                    label = "Gestión de Citas Médicas",
                    onClick = { navController.navigate("cita_medica_screen") }
                )
                OptionIcon(
                    imageUrl = "https://drive.google.com/uc?export=download&id=1hx1fb_TMNPvGrj4pKJFBoc4JLVopWYdX",
                    label = "Ver Historias Médicas",
                    onClick = { navController.navigate("listar_historias") }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OptionIcon(
                    imageUrl = "https://drive.google.com/uc?export=download&id=1g3aZP_cwrgklj599QuLQsXYaDi4fcNl0",
                    label = "Gestion Disponibilidad Horaria",
                    onClick = { navController.navigate("disponibilidad_horaria") }
                )
            }
        }
    }
}

@Composable
fun OptionIcon(imageUrl: String, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = label,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}
