package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarDisponibilidadHorariaScreen(
    disponibilidad: DisponibilidadHoraria,
    onActualizado: () -> Unit
) {
    val viewModel: DisponibilidadHorariaViewModel = viewModel()
    var fechaHoraTexto by remember { mutableStateOf(disponibilidad.fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))) }
    var estaDisponible by remember { mutableStateOf(disponibilidad.disponible) }
    var errorMensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Actualizar Disponibilidad") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = fechaHoraTexto,
                onValueChange = { fechaHoraTexto = it },
                label = { Text("Fecha y Hora (yyyy-MM-dd HH:mm)") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(
                    checked = estaDisponible,
                    onCheckedChange = { estaDisponible = it }
                )
                Text("¿Está disponible?")
            }

            if (errorMensaje.isNotEmpty()) {
                Text(text = errorMensaje, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                try {
                    val nuevaFechaHora = LocalDateTime.parse(fechaHoraTexto, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    val actualizada = disponibilidad.copy(fechaHora = nuevaFechaHora, disponible = estaDisponible)
                    viewModel.actualizarDisponibilidad(actualizada.id, actualizada)
                    onActualizado()
                } catch (e: Exception) {
                    errorMensaje = "Formato inválido."
                }
            }) {
                Text("Actualizar")
            }
        }
    }
}
