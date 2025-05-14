package com.example.proyectomedilink.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectomedilink.viewmodel.PacienteViewModel
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAA000000))
        )

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Gestión de Pacientes", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Opciones de Paciente", color = Color.White)

                Spacer(modifier = Modifier.height(24.dp))

                // Opciones en forma de iconos
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1b-I3tLWD9q9VTREbc31F8E-tzQGswY63", // Agregar Paciente
                        label = "Agregar Paciente",
                        onClick = { navController.navigate("agregar_paciente") }
                    )
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1Wz9RAEBzmumS7uHjiQlnvbrzauiQl7Yx", // Listar Pacientes
                        label = "Listar Pacientes",
                        onClick = { navController.navigate("listar_pacientes") }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1GHqgkPUQwY_ZRPsDtZQr9BwT_fMKxRKD", // Actualizar Paciente
                        label = "Actualizar Paciente",
                        onClick = { navController.navigate("actualizar_paciente") }
                    )
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1hx1fb_TMNPvGrj4pKJFBoc4JLVopWYdX", // Eliminar Paciente
                        label = "Eliminar Paciente",
                        onClick = { navController.navigate("eliminar_paciente") }
                    )
                }
            }
        }
    }
}
