package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectomedilink.Model.HistoriaMedica
import com.example.proyectomedilink.viewmodel.HistoriaMedicaViewModel
import java.time.LocalDate
import java.time.format.DateTimeParseException
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarHistoriaMedicaScreen(
    navController: NavHostController,
    viewModel: HistoriaMedicaViewModel
) {
    var diagnostico by remember { mutableStateOf("") }
    var tratamiento by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(LocalDate.now().toString()) }
    var pacienteId by remember { mutableStateOf("") }
    var medicoId by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agregar Historia Médica") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = paddingValues.calculateTopPadding()),
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
                Text(text = it, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    // Validar campos vacíos
                    if (diagnostico.isBlank() || tratamiento.isBlank() || fecha.isBlank()
                        || pacienteId.isBlank() || medicoId.isBlank()
                    ) {
                        errorMsg = "Todos los campos son obligatorios"
                        return@Button
                    }
                    // Validar fecha correcta
                    val fechaLocalDate = try {
                        LocalDate.parse(fecha)
                    } catch (e: DateTimeParseException) {
                        errorMsg = "Formato de fecha inválido"
                        return@Button
                    }

                    // Crear historia médica
                    val nuevaHistoria = HistoriaMedica(
                        id = 0L,
                        diagnostico = diagnostico.trim(),
                        tratamiento = tratamiento.trim(),
                        fecha = fechaLocalDate,
                        pacienteId = pacienteId.toLong(),
                        medicoId = medicoId.toLong()
                    )

                    viewModel.guardarHistoria(nuevaHistoria)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}
*/