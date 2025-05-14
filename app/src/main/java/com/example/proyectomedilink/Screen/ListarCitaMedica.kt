package com.example.proyectomedilink.Screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectomedilink.Model.CitaMedica
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel

@Composable
fun ListarCitaMedicaScreen(
    viewModel: CitaMedicaViewModel = viewModel(),
    navController: NavController
) {
    val citas by viewModel.citas.observeAsState(emptyList())
    val pacientes by viewModel.pacientes.observeAsState(emptyList())
    val medicos by viewModel.medicos.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    Column(modifier = Modifier.padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Text(
                text = "Listado de Citas Médicas",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (citas.isEmpty()) {
                Text("No hay citas médicas registradas.")
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(citas) { cita ->
                        val paciente = pacientes.firstOrNull { it.id == cita.pacienteId }
                        val medico = medicos.firstOrNull { it.id == cita.medicoId }

                        CitaMedicaItem(
                            cita = cita,
                            paciente = paciente,
                            medico = medico,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CitaMedicaItem(
    cita: CitaMedica,
    paciente: Paciente?,
    medico: Medico?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Datos del Paciente
            paciente?.let {
                Text("Paciente: ${it.nombre}", style = MaterialTheme.typography.bodyLarge)
                Text("Documento: ${it.documento}", style = MaterialTheme.typography.bodyMedium)
            } ?: Text("Paciente no encontrado", color = Color.Red)

            Spacer(modifier = Modifier.height(8.dp))

            // Datos del Médico
            medico?.let {
                Text("Médico: ${it.nombre}", style = MaterialTheme.typography.bodyLarge)
                Text("Especialidad: ${it.especialidad}", style = MaterialTheme.typography.bodyMedium)
            } ?: Text("Médico no encontrado", color = Color.Red)

            Spacer(modifier = Modifier.height(8.dp))

            // Detalles de la Cita
            Text("Fecha: ${cita.fecha}", style = MaterialTheme.typography.bodyMedium)
            Text("Hora: ${cita.hora}", style = MaterialTheme.typography.bodyMedium)
            Text("Motivo: ${cita.motivo}", style = MaterialTheme.typography.bodyMedium)

            // Estado con color condicional
            Text(
                text = "Estado: ${cita.estado}",
                color = when (cita.estado) {
                    "Cancelada" -> Color.Red
                    "Completada" -> Color.Green
                    else -> Color.Unspecified
                }
            )
        }
    }
}