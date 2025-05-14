package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment // Asegúrate de importar esto
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectomedilink.viewmodel.MedicoViewModel

@Composable
fun EliminarMedicoScreen(viewModel: MedicoViewModel, navController: NavController) {
    var idInput by remember { mutableStateOf("") }
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
            label = { Text("ID del médico a eliminar") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Convertir idInput a Long
                val id = idInput.toLongOrNull()
                if (id != null && id > 0) {
                    // Llamar al método de eliminación solo si el ID es válido
                    viewModel.eliminarMedico(id)
                    mensaje = "Médico eliminado"
                    errorMessage = ""
                } else {
                    errorMessage = "Por favor ingrese un ID válido"
                    mensaje = ""
                }
            },
            modifier = Modifier.align(Alignment.End) // Asegúrate de que Alignment esté importado
        ) {
            Text("Eliminar")
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
