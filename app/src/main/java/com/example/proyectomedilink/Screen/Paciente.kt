package com.example.proyectomedilink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectomedilink.viewmodel.PacienteViewModel
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteScreen(navController: NavHostController, viewModel: PacienteViewModel) {
    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1aWmUH72C7hQs1duzcJfRCzeiREoARJjy"

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Capa oscura con degradado translúcido
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0x99000000), Color(0xDD000000))
                    )
                )
        )

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        "Gestión de Pacientes",
                        color = Color.White,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Opciones de Paciente",
                    color = Color(0xFFE1F5FE),
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PacienteOptionCard(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1b-I3tLWD9q9VTREbc31F8E-tzQGswY63",
                        label = "Agregar Paciente",
                        onClick = { navController.navigate("agregar_paciente") }
                    )
                    PacienteOptionCard(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1Wz9RAEBzmumS7uHjiQlnvbrzauiQl7Yx",
                        label = "Listar Pacientes",
                        onClick = { navController.navigate("listar_pacientes") }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PacienteOptionCard(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1GHqgkPUQwY_ZRPsDtZQr9BwT_fMKxRKD",
                        label = "Actualizar Paciente",
                        onClick = { navController.navigate("actualizar_paciente") }
                    )
                    PacienteOptionCard(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1hx1fb_TMNPvGrj4pKJFBoc4JLVopWYdX",
                        label = "Eliminar Paciente",
                        onClick = { navController.navigate("eliminar_paciente") }
                    )
                }
            }
        }
    }
}

@Composable
fun PacienteOptionCard(imageUrl: String, label: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E88E5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(140.dp, 160.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = label,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = label,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}