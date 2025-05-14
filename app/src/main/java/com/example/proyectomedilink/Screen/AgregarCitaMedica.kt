package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarCitaScreen(
    viewModel: CitaMedicaViewModel = viewModel(),
    navController: NavController
) {
    val pacientes by viewModel.pacientes.observeAsState(emptyList())
    val medicos by viewModel.medicos.observeAsState(emptyList())
    val operationSuccess by viewModel.operationSuccess.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)

    var selectedPaciente by remember { mutableStateOf<Paciente?>(null) }
    var pacienteDropdownExpanded by remember { mutableStateOf(false) }

    var selectedMedico by remember { mutableStateOf<Medico?>(null) }
    var medicoDropdownExpanded by remember { mutableStateOf(false) }

    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("Programada") }

    // Navegar atrás si la operación fue exitosa
    LaunchedEffect(operationSuccess) {
        if (operationSuccess == true) {
            navController.popBackStack()
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Paciente Dropdown
        Text("Seleccionar Paciente:", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        ExposedDropdownMenuBox(
            expanded = pacienteDropdownExpanded,
            onExpandedChange = { pacienteDropdownExpanded = it }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                value = selectedPaciente?.nombre ?: "",
                onValueChange = {},
                label = { Text("Paciente") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = pacienteDropdownExpanded)
                }
            )
            ExposedDropdownMenu(
                expanded = pacienteDropdownExpanded,
                onDismissRequest = { pacienteDropdownExpanded = false }
            ) {
                pacientes.forEach { paciente ->
                    DropdownMenuItem(
                        text = { Text(paciente.nombre) },
                        onClick = {
                            selectedPaciente = paciente
                            pacienteDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Médico Dropdown
        Text("Seleccionar Médico:", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        ExposedDropdownMenuBox(
            expanded = medicoDropdownExpanded,
            onExpandedChange = { medicoDropdownExpanded = it }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                value = selectedMedico?.nombre ?: "",
                onValueChange = {},
                label = { Text("Médico") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = medicoDropdownExpanded)
                }
            )
            ExposedDropdownMenu(
                expanded = medicoDropdownExpanded,
                onDismissRequest = { medicoDropdownExpanded = false }
            ) {
                medicos.forEach { medico ->
                    DropdownMenuItem(
                        text = { Text(medico.nombre) },
                        onClick = {
                            selectedMedico = medico
                            medicoDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de fecha y hora
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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = motivo,
            onValueChange = { motivo = it },
            label = { Text("Motivo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de Estado
        val estados = listOf("Programada", "Completada", "Cancelada")
        var estadoExpanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = estadoExpanded,
            onExpandedChange = { estadoExpanded = it }
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
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = estadoExpanded)
                }
            )
            ExposedDropdownMenu(
                expanded = estadoExpanded,
                onDismissRequest = { estadoExpanded = false }
            ) {
                estados.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            estado = opcion
                            estadoExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Guardar
        Button(
            onClick = {
                val pacienteId = selectedPaciente?.id
                val medicoId = selectedMedico?.id
                if (pacienteId != null && medicoId != null) {
                    viewModel.agregarCitaMedica(
                        pacienteId = pacienteId,
                        medicoId = medicoId,
                        motivo = motivo,
                        estado = estado,
                        fecha = fecha,
                        hora = hora
                    )
                } else {
                    viewModel.setErrorMessage("Seleccione un paciente y un médico")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Cita Médica")
        }

        // Mensajes de error
        errorMessage?.let { msg ->
            if (msg.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = msg,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}