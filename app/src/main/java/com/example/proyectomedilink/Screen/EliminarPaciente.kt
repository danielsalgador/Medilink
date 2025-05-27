package com.example.proyectomedilink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectomedilink.viewmodel.PacienteViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.proyectomedilink.component.StyledTextField

@Composable
fun EliminarPacienteScreen(viewModel: PacienteViewModel, navController: NavController) {
    var idInput by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val pacientes by viewModel.pacientes.observeAsState(emptyList())

    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1Usw89tSEfT2jiSZpwgKc-PRSYWfes8hZ"

    Box(modifier = Modifier.fillMaxSize()) {

        // Imagen de fondo cargada desde URL con Coil
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Capa translúcida para mejorar legibilidad
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xCCF7F9F9))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            StyledTextField(
                value = idInput,
                placeholder = "ID del paciente a eliminar",
                onValueChange = { idInput = it.filter { c -> c.isDigit() } }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val id = idInput.toLongOrNull()
                    if (id == null) {
                        mensaje = "ID inválido"
                        return@Button
                    }

                    val pacienteExiste = pacientes.any { it.id == id }
                    if (!pacienteExiste) {
                        mensaje = "No existe paciente con ID $id"
                        return@Button
                    }

                    isLoading = true
                    viewModel.eliminarPaciente(id)
                    mensaje = "Paciente eliminado. Actualiza la lista si no ves cambios inmediatamente."
                    idInput = ""
                    isLoading = false
                },
                modifier = Modifier.align(Alignment.End),
                enabled = !isLoading
            ) {
                Text("Eliminar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (mensaje.isNotEmpty()) {
                Text(text = mensaje, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
