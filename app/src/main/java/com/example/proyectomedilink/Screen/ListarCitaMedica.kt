package com.example.proyectomedilink.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel
import java.time.format.DateTimeFormatter
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarCitasMedicasScreen(viewModel: CitaMedicaViewModel, navController: NavHostController) {
    val citas by viewModel.citasMedicas.observeAsState(emptyList())
    val pacientes by viewModel.pacientes.observeAsState(emptyList())
    val medicos by viewModel.medicos.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.cargarDatos()       // Carga pacientes y medicos primero
        viewModel.obtenerCitasMedicas()  // Luego carga citas
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Citas Médicas") }) }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = paddingValues.calculateBottomPadding() + 8.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            items(citas) { cita ->

                val paciente = pacientes.find { it.id == cita.pacienteId }
                val medico = medicos.find { it.id == cita.medicoId }

                val fechaFormateada = cita.fecha.toJavaLocalDate()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

                val horaFormateada = cita.hora.toJavaLocalTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm"))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Paciente: ${paciente?.nombre ?: "N/A"}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Médico: ${medico?.nombre ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Especialidad: ${medico?.especialidad ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Fecha: $fechaFormateada", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Hora: $horaFormateada", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Estado: ${cita.estado}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
