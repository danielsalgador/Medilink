package com.example.proyectomedilink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.repository.PacienteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PacienteViewModel : ViewModel() {

    private val repository = PacienteRepository()

    private val _pacientes = MutableLiveData<List<Paciente>>(emptyList())
    val pacientes: LiveData<List<Paciente>> get() = _pacientes

    fun obtenerPacientes() {
        viewModelScope.launch {
            try {
                val lista: List<Paciente> = withContext(Dispatchers.IO) {
                    repository.obtenerPacientes()
                }
                _pacientes.postValue(lista)
            } catch (e: Exception) {
                println("Error al obtener pacientes: ${e.message}")
            }
        }
    }

    fun actualizarPaciente(id: Long, paciente: Paciente) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.actualizarPaciente(id, paciente)
                }
                obtenerPacientes()
            } catch (e: Exception) {
                println("Error al actualizar paciente: ${e.message}")
            }
        }
    }

    fun eliminarPaciente(id: Long) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.eliminarPaciente(id)
                }
                obtenerPacientes()
            } catch (e: Exception) {
                println("Error al eliminar paciente: ${e.message}")
            }
        }
    }

    fun guardarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.guardarPaciente(paciente)
                }
                obtenerPacientes()
            } catch (e: Exception) {
                println("Error al guardar paciente: ${e.message}")
            }
        }
    }
}
