package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.viewmodel.MedicoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarMedicoScreen(
    navController: NavHostController,
    medicoViewModel: MedicoViewModel
) {
    val backgroundImageUrl =
        "https://drive.google.com/uc?export=download&id=1WYWuGSzoQZpDW4P668zhGYfb6umltEhi"

    // Estados para los campos
    var nombre by remember { mutableStateOf("") }
    var especialidad by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Agregar Médico", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del Médico") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = especialidad,
                onValueChange = { especialidad = it },
                label = { Text("Especialidad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Mostrar mensaje de error si falta algún campo
            errorMessage?.let { msg ->
                Text(text = msg, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = {
                    if (nombre.isNotBlank() &&
                        especialidad.isNotBlank() &&
                        correo.isNotBlank() &&
                        telefono.isNotBlank()
                    ) {
                        // Crear el objeto Medico y guardarlo
                        val nuevoMedico = Medico(
                            id = null,
                            nombre = nombre.trim(),
                            especialidad = especialidad.trim(),
                            correo = correo.trim(),
                            telefono = telefono.trim()
                        )
                        medicoViewModel.guardarMedico(nuevoMedico)
                        // Regresar al listado o pantalla principal de médicos
                        navController.navigate("medico_screen") {
                            popUpTo("medico_screen") { inclusive = true }
                        }
                    } else {
                        errorMessage = "Por favor, complete todos los campos."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Médico")
            }
        }
    }
}
