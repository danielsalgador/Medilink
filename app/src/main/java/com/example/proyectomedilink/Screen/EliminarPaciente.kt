package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectomedilink.viewmodel.PacienteViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun EliminarPacienteScreen(viewModel: PacienteViewModel, navController: NavController) {
    var idInput by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val pacientes by viewModel.pacientes.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = idInput,
            onValueChange = { idInput = it.filter { c -> c.isDigit() } },
            label = { Text("ID del paciente a eliminar") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val id = idInput.toLongOrNull()
                if (id != null) {
                    val antes = pacientes.size
                    viewModel.eliminarPaciente(id)

                    // IMPORTANTE: Espera un poco para que LiveData se actualice
                    mensaje = "Paciente eliminado. Actualiza la lista si no ves cambios inmediatamente."
                } else {
                    mensaje = "ID inválido"
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Eliminar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (mensaje.isNotEmpty()) {
            Text(text = mensaje, color = MaterialTheme.colorScheme.primary)
        }
    }
}
