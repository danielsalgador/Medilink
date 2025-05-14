package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectomedilink.viewmodel.PacienteViewModel
import com.example.proyectomedilink.Model.Paciente

@Composable
fun ActualizarPacienteScreen(viewModel: PacienteViewModel, navController: NavController) {
    var idInput by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var documento by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var condicion by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    var mensaje by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = idInput,
            onValueChange = { idInput = it },
            label = { Text("ID del paciente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nuevo nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Nuevo correo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Nuevo teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = documento,
            onValueChange = { documento = it },
            label = { Text("Documento") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = condicion,
            onValueChange = { condicion = it },
            label = { Text("Condición médica") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val id = idInput.toLongOrNull()

                if (id != null) {
                    val pacienteActualizado = Paciente(
                        id = id,
                        nombre = nombre,
                        documento = documento,
                        correo = correo,
                        telefono = telefono,
                        direccion = direccion,
                        condicion = condicion
                    )

                    viewModel.actualizarPaciente(id, pacienteActualizado)
                    mensaje = "Paciente actualizado exitosamente"
                    errorMessage = ""
                } else {
                    mensaje = ""
                    errorMessage = "ID o Edad no válidos"
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Actualizar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (mensaje.isNotEmpty()) {
            Text(text = mensaje, color = MaterialTheme.colorScheme.primary)
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
