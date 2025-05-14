package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectomedilink.Model.CitaMedica
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarCitaMedicaScreen(
    viewModel: CitaMedicaViewModel,
    navController: NavController,
    citaId: Long
) {
    // Estados para los campos del formulario
    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("Programada") }
    var expanded by remember { mutableStateOf(false) } // Estado para el menú desplegable
    var mensaje by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Cargar datos de la cita al inicializar
    LaunchedEffect(citaId) {
        viewModel.citas.value?.find { it.id == citaId }?.let { cita ->
            pacienteId = cita.pacienteId.toString()
            medicoId = cita.medicoId.toString()
            fecha = cita.fecha
            hora = cita.hora
            motivo = cita.motivo
            estado = cita.estado
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Actualizar Cita #$citaId",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo ID Paciente
        OutlinedTextField(
            value = pacienteId,
            onValueChange = { pacienteId = it },
            label = { Text("ID Paciente") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo ID Médico
        OutlinedTextField(
            value = medicoId,
            onValueChange = { medicoId = it },
            label = { Text("ID Médico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fecha y Hora en fila
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (AAAA-MM-DD)") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            OutlinedTextField(
                value = hora,
                onValueChange = { hora = it },
                label = { Text("Hora (HH:MM)") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Motivo
        OutlinedTextField(
            value = motivo,
            onValueChange = { motivo = it },
            label = { Text("Motivo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Selector de Estado mejorado
        val estados = listOf("Programada", "Completada", "Cancelada")
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                value = estado,
                onValueChange = {},
                label = { Text("Estado") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                estados.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            estado = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Actualizar
        Button(
            onClick = {
                if (validarCampos(pacienteId, medicoId, fecha, hora, motivo, estado)) {
                    viewModel.actualizarCitaMedica(
                        id = citaId,
                        pacienteId = pacienteId.toLong(),
                        medicoId = medicoId.toLong(),
                        motivo = motivo,
                        estado = estado,
                        fecha = fecha,
                        hora = hora
                    )
                    mensaje = "Cita actualizada correctamente"
                    navController.popBackStack()
                } else {
                    errorMessage = "Verifique todos los campos"
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Actualizar")
        }

        // Mensajes de feedback
        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
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
    return pacienteId.isNotBlank() && medicoId.isNotBlank() &&
            fecha.isNotBlank() && hora.isNotBlank() &&
            motivo.isNotBlank() && estado.isNotBlank() &&
            pacienteId.toLongOrNull() != null && medicoId.toLongOrNull() != null &&
            fecha.matches(Regex("\\d{4}-\\d{2}-\\d{2}")) &&
            hora.matches(Regex("\\d{2}:\\d{2}"))
}