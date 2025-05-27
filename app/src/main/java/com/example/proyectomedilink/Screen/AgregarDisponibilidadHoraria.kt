package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalTime
import java.time.format.DateTimeParseException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarDisponibilidadHoraria(
    medicoId: Long,
    onDisponibilidadAgregada: () -> Unit
) {
    val viewModel: DisponibilidadHorariaViewModel = viewModel()

    var fechaTexto by remember { mutableStateOf("") }
    var horaTexto by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agregar Disponibilidad") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = fechaTexto,
                onValueChange = {
                    fechaTexto = it
                    errorMensaje = null
                },
                label = { Text("Fecha (yyyy-MM-dd)") },
                isError = errorMensaje != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = horaTexto,
                onValueChange = {
                    horaTexto = it
                    errorMensaje = null
                },
                label = { Text("Hora (HH:mm)") },
                isError = errorMensaje != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            errorMensaje?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    try {
                        // Parsear usando kotlinx.datetime
                        val fecha = LocalDate.parse(fechaTexto)
                        val hora = LocalTime.parse(horaTexto)

                        val disponibilidad = DisponibilidadHoraria(
                            id = 0L,
                            fecha = fecha,
                            hora = hora,
                            disponible = true,
                            medicoId = medicoId
                        )

                        viewModel.guardarDisponibilidadHoraria(disponibilidad)

                        fechaTexto = ""
                        horaTexto = ""
                        errorMensaje = null
                        onDisponibilidadAgregada()

                    } catch (e: Exception) {
                        errorMensaje = "Formato inválido. Fecha: yyyy-MM-dd, Hora: HH:mm"
                    }
                },
                enabled = fechaTexto.isNotBlank() && horaTexto.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Agregar Disponibilidad", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
