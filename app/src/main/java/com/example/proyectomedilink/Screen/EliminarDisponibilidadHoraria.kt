package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EliminarDisponibilidadHorariaScreen(
    disponibilidad: DisponibilidadHoraria,
    onEliminado: () -> Unit
) {
    val viewModel: DisponibilidadHorariaViewModel = viewModel()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Eliminar Disponibilidad") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("¿Estás seguro de que deseas eliminar esta disponibilidad?:")
            Text("Fecha: ${disponibilidad.fechaHora}")
            Text("Disponible: ${disponibilidad.disponible}")

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = {
                    viewModel.eliminarDisponibilidad(disponibilidad.id, disponibilidad.medicoId)
                    onEliminado()
                }) {
                    Text("Eliminar")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = onEliminado) {
                    Text("Cancelar")
                }
            }
        }
    }
}
