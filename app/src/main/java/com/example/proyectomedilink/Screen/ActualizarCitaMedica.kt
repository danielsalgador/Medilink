package com.example.proyectomedilink.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectomedilink.Model.CitaMedica
import com.example.proyectomedilink.Model.EstadoCita
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarCitaMedicaScreen(
    viewModel: CitaMedicaViewModel,
    navController: NavController,
    citaId: Long
) {
    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(EstadoCita.Programada.name) }
    var expanded by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(citaId) {
        scope.launch {
            try {
                val cita = viewModel.obtenerCitaPorId(citaId)
                if (cita != null) {
                    pacienteId = cita.pacienteId.toString()
                    medicoId = cita.medicoId.toString()
                    fecha = cita.fecha.toString()
                    hora = cita.hora.toString()
                    motivo = cita.motivo
                    estado = cita.estado.name
                } else {
                    errorMessage = "No se encontró la cita con ID $citaId"
                }
            } catch (e: Exception) {
                errorMessage = "Error al cargar la cita: ${e.message}"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Actualizar Cita #$citaId", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pacienteId,
            onValueChange = { pacienteId = it },
            label = { Text("ID Paciente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = medicoId,
            onValueChange = { medicoId = it },
            label = { Text("ID Médico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (AAAA-MM-DD)") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = hora,
                onValueChange = { hora = it },
                label = { Text("Hora (HH:MM)") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = motivo,
            onValueChange = { motivo = it },
            label = { Text("Motivo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        val estados = EstadoCita.values().map { it.name }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = estado,
                onValueChange = {},
                readOnly = true,
                label = { Text("Estado") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                estados.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            estado = opcion
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (validarCampos(pacienteId, medicoId, fecha, hora, motivo, estado)) {
                    scope.launch {
                        try {
                            val fechaParsed = LocalDate.parse(fecha)
                            val horaParsed = LocalTime.parse(hora)
                            val estadoParsed = EstadoCita.valueOf(estado)

                            // LLamada al ViewModel con el objeto CitaMedica
                            viewModel.actualizarCitaMedica(
                                citaId,
                                pacienteId.toLong(),
                                medicoId.toLong(),
                                motivo,
                                estado,
                                fecha,
                                hora
                            )

                            mensaje = "Cita actualizada correctamente"
                            navController.popBackStack()
                        } catch (e: Exception) {
                            errorMessage = "Error al actualizar: ${e.message}"
                        }
                    }
                } else {
                    errorMessage = "Verifique todos los campos"
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Actualizar")
        }

        if (mensaje.isNotEmpty()) {
            Text(mensaje, color = MaterialTheme.colorScheme.primary)
        }
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

private fun validarCampos(
    pacienteId: String,
    medicoId: String,
    fecha: String,
    hora: String,
    motivo: String,
    estado: String
): Boolean {
    return pacienteId.toLongOrNull() != null &&
            medicoId.toLongOrNull() != null &&
            fecha.matches(Regex("\\d{4}-\\d{2}-\\d{2}")) &&
            hora.matches(Regex("\\d{2}:\\d{2}")) &&
            motivo.isNotBlank() &&
            try {
                EstadoCita.valueOf(estado)
                true
            } catch (e: Exception) {
                false
            }
}
