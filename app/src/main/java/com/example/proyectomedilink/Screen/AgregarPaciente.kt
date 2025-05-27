package com.example.proyectomedilink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.viewmodel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarPacienteScreen(
    navController: NavHostController,
    pacienteViewModel: PacienteViewModel
) {
    val backgroundImageUrl =
        "https://drive.google.com/uc?export=download&id=1lt73QgCegwvpTGbgsK5S8C_nEhYVKqEI"

    var nombre by remember { mutableStateOf("") }
    var documento by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var condicion by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Capa semitransparente para mejorar legibilidad
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Agregar Paciente",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campos de texto con validación básica en tiempo real (puedes mejorarla)
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = nombre.isBlank()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = documento,
                onValueChange = { documento = it },
                label = { Text("Documento") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = documento.isBlank()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = correo.isBlank()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = telefono.isBlank()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = direccion.isBlank()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = condicion,
                onValueChange = { condicion = it },
                label = { Text("Condición") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = condicion.isBlank()
            )
            Spacer(modifier = Modifier.height(24.dp))

            errorMessage?.let { msg ->
                Text(
                    text = msg,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = {
                    if (nombre.isNotBlank() &&
                        documento.isNotBlank() &&
                        correo.isNotBlank() &&
                        telefono.isNotBlank() &&
                        direccion.isNotBlank() &&
                        condicion.isNotBlank()
                    ) {
                        val nuevoPaciente = Paciente(
                            id = null,
                            nombre = nombre.trim(),
                            documento = documento.trim(),
                            correo = correo.trim(),
                            telefono = telefono.trim(),
                            direccion = direccion.trim(),
                            condicion = condicion.trim()
                        )
                        pacienteViewModel.guardarPaciente(nuevoPaciente)

                        navController.navigate("paciente_screen") {
                            popUpTo("paciente_screen") { inclusive = true }
                        }
                    } else {
                        errorMessage = "Por favor, complete todos los campos."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Paciente")
            }
        }
    }
}
