package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EliminarDisponibilidadHorariaScreen(
    disponibilidad: DisponibilidadHoraria,
    onEliminado: () -> Unit
) {
    val viewModel: DisponibilidadHorariaViewModel = viewModel()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Eliminar Disponibilidad") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("¿Estás seguro de que deseas eliminar esta disponibilidad?")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Fecha: ${disponibilidad.fecha}")
            Text("Hora: ${disponibilidad.hora}")
            Text("Disponible: ${if (disponibilidad.disponible) "Sí" else "No"}")

            Spacer(modifier = Modifier.height(16.dp))

            errorMessage?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Row {
                Button(
                    onClick = {
                        isLoading = true
                        errorMessage = null
                        try {
                            viewModel.eliminarDisponibilidad(disponibilidad.id, disponibilidad.medicoId)
                            onEliminado()
                        } catch (e: Exception) {
                            errorMessage = "Error al eliminar: ${e.message}"
                        } finally {
                            isLoading = false
                        }
                    },
                    enabled = !isLoading
                ) {
                    Text(if (isLoading) "Eliminando..." else "Eliminar")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = onEliminado,
                    enabled = !isLoading
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}
