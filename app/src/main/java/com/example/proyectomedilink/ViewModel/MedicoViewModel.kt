package com.example.proyectomedilink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.repository.MedicoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MedicoViewModel : ViewModel() {

    private val repository = MedicoRepository()
    private val _medicos = MutableLiveData<List<Medico>>(emptyList())
    val medicos: LiveData<List<Medico>> get() = _medicos

    fun obtenerMedicos() {
        viewModelScope.launch {
            try {
                val lista = withContext(Dispatchers.IO) {
                    repository.obtenerMedicos()
                }
                _medicos.postValue(lista)
            } catch (e: Exception) {
                println("Error al obtener médicos: ${e.message}")
            }
        }
    }

    fun guardarMedico(medico: Medico) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.guardarMedico(medico)
                }
                obtenerMedicos()
            } catch (e: Exception) {
                println("Error al guardar médico: ${e.message}")
            }
        }
    }

    fun actualizarMedico(medico: Medico) {
        viewModelScope.launch {
            medico.id?.let { id ->
                try {
                    withContext(Dispatchers.IO) {
                        repository.actualizarMedico(id, medico)
                    }
                    obtenerMedicos()
                } catch (e: Exception) {
                    println("Error al actualizar médico: ${e.message}")
                }
            } ?: println("Error: ID del médico es nulo")
        }
    }

    fun eliminarMedico(id: Long) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.eliminarMedico(id)
                }
                obtenerMedicos()
            } catch (e: Exception) {
                println("Error al eliminar médico: ${e.message}")
            }
        }
    }
}
