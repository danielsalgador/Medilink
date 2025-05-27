package com.example.proyectomedilink.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarDisponibilidadHorariaScreen(
    disponibilidad: DisponibilidadHoraria,
    onActualizado: () -> Unit
) {
    val viewModel: DisponibilidadHorariaViewModel = viewModel()

    // Variables separadas para fecha y hora en formato String
    var fechaTexto by remember { mutableStateOf(disponibilidad.fecha.toString()) } // yyyy-MM-dd
    var horaTexto by remember { mutableStateOf(disponibilidad.hora.toString()) }   // HH:mm:ss o HH:mm
    var estaDisponible by remember { mutableStateOf(disponibilidad.disponible) }
    var errorMensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Actualizar Disponibilidad") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = fechaTexto,
                onValueChange = {
                    fechaTexto = it
                    errorMensaje = ""
                },
                label = { Text("Fecha (yyyy-MM-dd)") },
                isError = errorMensaje.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = horaTexto,
                onValueChange = {
                    horaTexto = it
                    errorMensaje = ""
                },
                label = { Text("Hora (HH:mm)") },
                isError = errorMensaje.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(
                    checked = estaDisponible,
                    onCheckedChange = { estaDisponible = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("¿Está disponible?")
            }

            if (errorMensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorMensaje, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    try {
                        val nuevaFecha = LocalDate.parse(fechaTexto)
                        val nuevaHora = LocalTime.parse(horaTexto)

                        val actualizada = disponibilidad.copy(
                            fecha = nuevaFecha,
                            hora = nuevaHora,
                            disponible = estaDisponible
                        )
                        viewModel.actualizarDisponibilidad(actualizada.id, actualizada)
                        onActualizado()
                    } catch (e: Exception) {
                        errorMensaje = "Formato inválido. Fecha: yyyy-MM-dd, Hora: HH:mm"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar")
            }
        }
    }
}
