package com.example.proyectomedilink.screen

import ListarDisponibilidadHorariaScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisponibilidadHorariaScreen(
    navController: NavController,
    medicoId: Long,
    viewModel: DisponibilidadHorariaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    ListarDisponibilidadHorariaScreen(
        medicoId = medicoId,
        viewModel = viewModel,
        onEditar = { disponibilidad ->
            navController.navigate("actualizar_disponibilidad/${disponibilidad.id}")
        },
        onEliminar = { disponibilidad ->
            navController.navigate("eliminar_disponibilidad/${disponibilidad.id}")
        }
    )
}
