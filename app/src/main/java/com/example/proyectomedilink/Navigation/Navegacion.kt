package com.example.proyectomedilink.Navigation

import ListarDisponibilidadHorariaScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyectomedilink.screen.AgregarCitaScreen
import com.example.proyectomedilink.screen.*
import com.example.proyectomedilink.screen.ActualizarCitaMedicaScreen
import com.example.proyectomedilink.screen.EliminarCitaMedicaScreen
import com.example.proyectomedilink.screen.MedicoScreen
import com.example.proyectomedilink.viewmodel.*
import com.example.proyectomedilink.viewmodel.MedicoViewModel
import com.example.proyectomedilink.screen.ActualizarMedicoScreen
import com.example.proyectomedilink.screen.ActualizarPacienteScreen
import com.example.proyectomedilink.screen.AgregarMedicoScreen
import com.example.proyectomedilink.screen.AgregarPacienteScreen
import com.example.proyectomedilink.screen.CitaMedicaScreen
import com.example.proyectomedilink.screen.DisponibilidadHorariaScreen
import com.example.proyectomedilink.screen.EliminarMedicoScreen
import com.example.proyectomedilink.screen.EliminarPacienteScreen
import com.example.proyectomedilink.screen.ListarCitasMedicasScreen
import com.example.proyectomedilink.screen.ListarMedicosScreen
import com.example.proyectomedilink.screen.ListarPacientesScreen
import com.example.proyectomedilink.screen.NextScreen
import com.example.proyectomedilink.screen.PacienteScreen
import com.example.proyectomedilink.screen.UserTypeScreen


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
            val medicoVM: MedicoViewModel = viewModel()
            MedicoScreen(navController, medicoVM)
        }
        composable("agregar_medico") {
            val medicoVM: MedicoViewModel = viewModel()
            AgregarMedicoScreen(navController, medicoVM)
        }
        composable("listar_medicos") {
            val medicoVM: MedicoViewModel = viewModel()
            ListarMedicosScreen(medicoVM, navController)
        }
        composable("actualizar_medico") {
            val medicoVM: MedicoViewModel = viewModel()
            ActualizarMedicoScreen(medicoVM, navController)
        }
        composable("eliminar_medico") {
            val medicoVM: MedicoViewModel = viewModel()
            EliminarMedicoScreen(medicoVM, navController)
        }

        // Paciente
        composable("paciente_screen") {
            val pacienteVM: PacienteViewModel = viewModel()
            PacienteScreen(navController, pacienteVM)
        }
        composable("agregar_paciente") {
            val pacienteVM: PacienteViewModel = viewModel()
            AgregarPacienteScreen(navController, pacienteVM)
        }
        composable("listar_pacientes") {
            val pacienteVM: PacienteViewModel = viewModel()
            ListarPacientesScreen(pacienteVM, navController)
        }
        composable("actualizar_paciente") {
            val pacienteVM: PacienteViewModel = viewModel()
            ActualizarPacienteScreen(pacienteVM, navController)
        }
        composable("eliminar_paciente") {
            val pacienteVM: PacienteViewModel = viewModel()
            EliminarPacienteScreen(pacienteVM, navController)
        }

        // Cita Médica
        composable("cita_medica_screen") {
            val citaMedicaVM: CitaMedicaViewModel = viewModel()
            CitaMedicaScreen(navController, citaMedicaVM, citaId = 0L)
        }
        composable("agregar_cita_medica") {
            val citaMedicaVM: CitaMedicaViewModel = viewModel()
            AgregarCitaScreen(navController, citaMedicaVM)
        }
        composable("listar_citas_medicas") {
            val citaMedicaVM: CitaMedicaViewModel = viewModel()
            ListarCitasMedicasScreen(citaMedicaVM, navController)
        }
        composable(
            "actualizar_cita_medica/{citaId}",
            arguments = listOf(navArgument("citaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val citaId = backStackEntry.arguments?.getLong("citaId") ?: 0L
            val citaMedicaVM: CitaMedicaViewModel = viewModel()
            ActualizarCitaMedicaScreen(citaMedicaVM, navController, citaId)
        }
        composable(
            "eliminar_cita_medica/{citaId}",
            arguments = listOf(navArgument("citaId") { type = NavType.LongType })
        ) { backStackEntry ->
            val citaId = backStackEntry.arguments?.getLong("citaId") ?: 0L
            val citaMedicaVM: CitaMedicaViewModel = viewModel()
            EliminarCitaMedicaScreen(citaId, citaMedicaVM, navController)
        }

        // Disponibilidad Horaria
        composable("disponibilidad_horaria") {
            val disponibilidadVM: DisponibilidadHorariaViewModel = viewModel()
            val medicoId = disponibilidadVM.medicoId
            if (medicoId != null) {
                DisponibilidadHorariaScreen(navController, medicoId)
            } else {
                NextScreen(navController)
            }
        }

        composable(
            "listar_disponibilidad/{medicoId}",
            arguments = listOf(navArgument("medicoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val medicoId = backStackEntry.arguments?.getLong("medicoId") ?: 0L
            val disponibilidadVM: DisponibilidadHorariaViewModel = viewModel()
            ListarDisponibilidadHorariaScreen(
                medicoId,
                disponibilidadVM,
                onEditar = { d -> navController.navigate("editar_disponibilidad/${d.id}") },
                onEliminar = { d -> disponibilidadVM.eliminarDisponibilidad(d.id!!, d.medicoId) }
            )
        }
    }
}
