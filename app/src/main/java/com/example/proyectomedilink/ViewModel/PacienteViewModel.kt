package com.example.proyectomedilink.viewmodel

import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.Screen.AgregarPacienteScreen
import com.example.proyectomedilink.repository.PacienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PacienteViewModel : ViewModel() {

    private val repository = PacienteRepository()
    private val _pacientes = MutableLiveData<List<Paciente>>(emptyList())
    val pacientes: LiveData<List<Paciente>> get() = _pacientes  // Exponer solo LiveData para observación

    // Función para obtener todos los pacientes de forma asincrónica
    fun obtenerPacientes() {
        viewModelScope.launch {
            try{
                val lista = withContext(Dispatchers.IO) {
                    repository.obtenerPacientes()
                }
                _pacientes.postValue(lista)
            } catch (e: Exception){
                print("Error al abtener Pacienetes: ${e.message}")
            }
        }
    }

    // Función para actualizar un paciente existente
    fun actualizarPaciente(id: Long, paciente: Paciente) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.actualizarPaciente(id, paciente)
            }
            obtenerPacientes()
        }
    }

    // Función para eliminar un paciente
    fun eliminarPaciente(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.eliminarPaciente(id)
            }
            obtenerPacientes()
        }
    }

    fun agregarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    // Suponiendo que el repositorio guarda un paciente y maneja el id automáticamente
                    repository.guardarPaciente(paciente)
                }
                obtenerPacientes()  // Actualizar la lista de pacientes
            } catch (e: Exception) {
                println("Error al guardar paciente: ${e.message}")
            }
        }
    }



    @Composable
    fun AgregarPacienteScreenWrapper(navController: NavHostController) {
        // Usando el viewModel a través de la función de composición
        val pacienteViewModel: PacienteViewModel = viewModel()

        // Pasando el ViewModel y NavController al Composable
        AgregarPacienteScreen(navController, pacienteViewModel)

    }
}
