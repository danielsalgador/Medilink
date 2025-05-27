package com.example.proyectomedilink.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectomedilink.Model.HistoriaMedica
import com.example.proyectomedilink.viewmodel.HistoriaMedicaViewModel
import java.time.LocalDate

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarHistoriaMedicaScreen(
    navController: NavHostController,
    historiaId: Long,
    viewModel: HistoriaMedicaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    // Observamos la historia individual
    val historia by viewModel.historiaMedica.observeAsState()

    // Cargamos la historia cuando entra el composable
    LaunchedEffect(historiaId) {
        viewModel.obtenerHistoria(historiaId)
    }

    historia?.let { h ->
        var diagnostico by remember { mutableStateOf(h.diagnostico) }
        var tratamiento by remember { mutableStateOf(h.tratamiento) }
        var fecha by remember { mutableStateOf(h.fecha.toString()) }
        var pacienteId by remember { mutableStateOf(h.pacienteId.toString()) }
        var medicoId by remember { mutableStateOf(h.medicoId.toString()) }
        var errorMsg by remember { mutableStateOf<String?>(null) }

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Actualizar Historia") })
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = padding.calculateTopPadding()),
                verticalArrangement = Arrangement.Top
            ) {
                OutlinedTextField(
                    value = diagnostico,
                    onValueChange = { diagnostico = it },
                    label = { Text("Diagnóstico") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tratamiento,
                    onValueChange = { tratamiento = it },
                    label = { Text("Tratamiento") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = pacienteId,
                    onValueChange = { pacienteId = it.filter { c -> c.isDigit() } },
                    label = { Text("ID Paciente") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = medicoId,
                    onValueChange = { medicoId = it.filter { c -> c.isDigit() } },
                    label = { Text("ID Médico") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                errorMsg?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Button(
                    onClick = {
                        if (diagnostico.isBlank() || tratamiento.isBlank() || fecha.isBlank()
                            || pacienteId.isBlank() || medicoId.isBlank()
                        ) {
                            errorMsg = "Todos los campos son obligatorios"
                        } else {
                            val historiaActualizada = HistoriaMedica(
                                id = h.id,
                                diagnostico = diagnostico.trim(),
                                tratamiento = tratamiento.trim(),
                                fecha = LocalDate.parse(fecha),
                                pacienteId = pacienteId.toLong(),
                                medicoId = medicoId.toLong()
                            )
                            viewModel.actualizarHistoria(h.id, historiaActualizada)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Actualizar")
                }
            }
        }
    } ?: run {
        // Indicador de carga mientras obtenemos la historia
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }*/

