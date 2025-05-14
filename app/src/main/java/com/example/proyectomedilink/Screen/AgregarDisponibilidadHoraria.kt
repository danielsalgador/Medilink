package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarDisponibilidadHorariaScreen(medicoId: Long, onDisponibilidadAgregada: () -> Unit) {
    val viewModel: DisponibilidadHorariaViewModel = viewModel()
    var fechaHoraTexto by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Agregar Disponibilidad") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = fechaHoraTexto,
                onValueChange = { fechaHoraTexto = it },
                label = { Text("Fecha y Hora (yyyy-MM-dd HH:mm)") },
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMensaje.isNotEmpty()) {
                Text(text = errorMensaje, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                try {
                    val fechaHora = LocalDateTime.parse(fechaHoraTexto, formatter)
                    viewModel.guardarDisponibilidad(
                        com.example.proyectomedilink.Model.DisponibilidadHoraria(
                            id = 0,
                            fechaHora = fechaHora,
                            disponible = true,
                            medicoId = medicoId
                        )
                    )
                    onDisponibilidadAgregada()
                } catch (e: Exception) {
                    errorMensaje = "Formato de fecha inválido."
                }
            }) {
                Text("Guardar Disponibilidad")
            }
        }
    }
}
