package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment // Importar la clase Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.viewmodel.MedicoViewModel

@Composable
fun ActualizarMedicoScreen(viewModel: MedicoViewModel, navController: NavController) {
    var idInput by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var especialidad by remember { mutableStateOf("") }
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
            label = { Text("ID del médico") },
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
            value = especialidad,
            onValueChange = { especialidad = it },
            label = { Text("Nueva especialidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val id = idInput.toLongOrNull()
                if (id != null && id > 0) {
                    val medicoActualizado = Medico(
                        id = id,
                        nombre = nombre,
                        especialidad = especialidad,
                        correo = "",     // Puedes agregar estos campos al formulario si los necesitas
                        telefono = ""
                    )
                    viewModel.actualizarMedico(medicoActualizado) // ✅ Corrección aquí
                    mensaje = "Médico actualizado"
                    errorMessage = ""
                } else {
                    errorMessage = "Por favor ingrese un ID válido"
                    mensaje = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Actualizar")
        }


        Spacer(modifier = Modifier.height(8.dp))

        // Mostrar mensaje de éxito o error
        if (mensaje.isNotEmpty()) {
            Text(text = mensaje, color = MaterialTheme.colorScheme.primary)
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
