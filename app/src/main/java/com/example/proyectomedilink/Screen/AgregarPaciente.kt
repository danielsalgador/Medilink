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
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.viewmodel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarPacienteScreen(
    navController: NavHostController,
    pacienteViewModel: PacienteViewModel
) {
    val backgroundImageUrl =
        "https://drive.google.com/uc?export=download&id=1lt73QgCegwvpTGbgsK5S8C_nEhYVKqEI"  // Nueva URL de la imagen de fondo

    // Estados para los campos
    var nombre by remember { mutableStateOf("") }
    var documento by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var condicion by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Agregar Paciente", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = documento,
                onValueChange = { documento = it },
                label = { Text("Documento") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = condicion,
                onValueChange = { condicion = it },
                label = { Text("Condición") },
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
                        documento.isNotBlank() &&
                        correo.isNotBlank() &&
                        telefono.isNotBlank() &&
                        direccion.isNotBlank() &&
                        condicion.isNotBlank()
                    ) {
                        // Crear el objeto Paciente y guardarlo
                        val nuevoPaciente = Paciente(
                            id = null,  // El ID lo maneja el backend o la base de datos
                            nombre = nombre.trim(),
                            documento = documento.trim(),
                            correo = correo.trim(),
                            telefono = telefono.trim(),
                            direccion = direccion.trim(),
                            condicion = condicion.trim()
                        )
                        pacienteViewModel.agregarPaciente(nuevoPaciente)
                        // Regresar a la pantalla de lista de pacientes
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
