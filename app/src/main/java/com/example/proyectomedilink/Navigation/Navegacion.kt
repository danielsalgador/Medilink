package com.example.proyectomedilink.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyectomedilink.Screen.*
import com.example.proyectomedilink.viewmodel.*

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "user_type_screen") {
        composable("user_type_screen") {
            UserTypeScreen(navController)
        }

        composable("next_screen") {
            NextScreen(navController)
        }

        // Médico
        composable("medico_screen") {
            val viewModel: MedicoViewModel = viewModel()
            MedicoScreen(navController, viewModel)
        }
        composable("agregar_medico") {
            val viewModel: MedicoViewModel = viewModel()
            AgregarMedicoScreen(navController, viewModel)
        }
        composable("listar_medicos") {
            val viewModel: MedicoViewModel = viewModel()
            ListarMedicosScreen(viewModel, navController)
        }
        composable("actualizar_medico") {
            val viewModel: MedicoViewModel = viewModel()
            ActualizarMedicoScreen(viewModel, navController)
        }
        composable("eliminar_medico") {
            val viewModel: MedicoViewModel = viewModel()
            EliminarMedicoScreen(viewModel, navController)
        }

        // Paciente
        composable("paciente_screen") {
            val viewModel: PacienteViewModel = viewModel()
            PacienteScreen(navController, viewModel)
        }
        composable("agregar_paciente") {
            val viewModel: PacienteViewModel = viewModel()
            AgregarPacienteScreen(navController, viewModel)
        }
        composable("listar_pacientes") {
            val viewModel: PacienteViewModel = viewModel()
            ListarPacientesScreen(viewModel, navController)
        }
        composable("actualizar_paciente") {
            val viewModel: PacienteViewModel = viewModel()
            ActualizarPacienteScreen(viewModel, navController)
        }
        composable("eliminar_paciente") {
            val viewModel: PacienteViewModel = viewModel()
            EliminarPacienteScreen(viewModel, navController)
        }

        // Cita Médica
        composable("cita_medica_screen") { backStackEntry ->
            val citaId = backStackEntry.arguments?.getString("citaId")?.toLong() ?: 0L
            val viewModel: CitaMedicaViewModel = viewModel()
            CitaMedicaScreen(navController = navController, viewModel = viewModel, citaId = citaId)
        }

        composable("agregar_cita_medica") {
            val viewModel: CitaMedicaViewModel = viewModel()
            AgregarCitaScreen(viewModel, navController)
        }
        composable("listar_citas_medicas") {
            val viewModel: CitaMedicaViewModel = viewModel()
            ListarCitaMedicaScreen(viewModel, navController)
        }
        composable(
            "actualizar_cita_medica/{citaId}",
            arguments = listOf(navArgument("citaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val citaId = backStackEntry.arguments?.getLong("citaId") ?: 0L
            val viewModel: CitaMedicaViewModel = viewModel()
            ActualizarCitaMedicaScreen(viewModel, navController, citaId)
        }

        composable(
            "eliminar_cita_medica/{citaId}",
            arguments = listOf(navArgument("citaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val citaId = backStackEntry.arguments?.getLong("citaId") ?: 0L
            val viewModel: CitaMedicaViewModel = viewModel()
            EliminarCitaMedicaScreen(viewModel, navController, citaId)
        }

        // Historia Médica
        composable("listar_historias") {
            val viewModel: HistoriaMedicaViewModel = viewModel()
            ListarHistoriaMedica(navController, viewModel)
        }
        composable(
            "detalle_historia/{historiaId}",
            arguments = listOf(navArgument("historiaId") { type = NavType.LongType })
        ) {
            val historiaId = it.arguments?.getLong("historiaId") ?: 0L
            val viewModel: HistoriaMedicaViewModel = viewModel()
            HistoriaMedicaScreen(navController, historiaId, viewModel)
        }
        composable("agregar_historia") {
            val viewModel: HistoriaMedicaViewModel = viewModel()
            AgregarHistoriaMedicaScreen(navController, viewModel)
        }
        composable(
            "actualizar_historia/{historiaId}",
            arguments = listOf(navArgument("historiaId") { type = NavType.LongType })
        ) {
            val historiaId = it.arguments?.getLong("historiaId") ?: 0L
            val viewModel: HistoriaMedicaViewModel = viewModel()
            ActualizarHistoriaMedicaScreen(navController, historiaId, viewModel)
        }
        composable(
            "eliminar_historia/{historiaId}",
            arguments = listOf(navArgument("historiaId") { type = NavType.LongType })
        ) {
            val historiaId = it.arguments?.getLong("historiaId") ?: 0L
            val viewModel: HistoriaMedicaViewModel = viewModel()
            EliminarHistoriaMedicaScreen(navController, historiaId, viewModel)
        }

        // Disponibilidad Horaria
        composable("disponibilidad_horaria") {
            val viewModel: DisponibilidadHorariaViewModel = viewModel()
            val medicoId = viewModel.medicoId
            if (medicoId != null) {
                DisponibilidadHorariaScreen(navController, medicoId)
            } else {
                NextScreen(navController)
            }
        }
        composable(
            "listar_disponibilidad/{medicoId}",
            arguments = listOf(navArgument("medicoId") { type = NavType.LongType })
        ) {
            val medicoId = it.arguments?.getLong("medicoId") ?: 0L
            val viewModel: DisponibilidadHorariaViewModel = viewModel()
            ListarDisponibilidadHorariaScreen(
                medicoId,
                viewModel,
                onEditar = { d -> navController.navigate("editar_disponibilidad/${d.id}") },
                onEliminar = { d -> viewModel.eliminarDisponibilidad(d.id!!, d.medicoId) }
            )
        }
        composable(
            "editar_disponibilidad/{disponibilidadId}",
            arguments = listOf(navArgument("disponibilidadId") { type = NavType.LongType })
        ) {
            // Implementa la pantalla de edición de disponibilidad
        }
    }
}