package com.example.proyectomedilink.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarDisponibilidadHorariaScreen(
    medicoId: Long,
    viewModel: DisponibilidadHorariaViewModel,
    onEditar: (DisponibilidadHoraria) -> Unit,
    onEliminar: (DisponibilidadHoraria) -> Unit
) {
    val disponibilidades by viewModel.disponibilidadesHorarias.observeAsState(emptyList())

    LaunchedEffect(medicoId) {
        viewModel.obtenerDisponibilidad(medicoId)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Disponibilidades Horarias") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            if (disponibilidades.isEmpty()) {
                Text("No hay disponibilidades registradas.")
            } else {
                disponibilidades.forEach { disponibilidad ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onEditar(disponibilidad) },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Fecha y Hora: ${disponibilidad.fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}")
                            Text("Disponible: ${if (disponibilidad.disponible) "Sí" else "No"}")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = { onEditar(disponibilidad) }) {
                                    Text("Editar")
                                }
                                TextButton(onClick = { onEliminar(disponibilidad) }) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
