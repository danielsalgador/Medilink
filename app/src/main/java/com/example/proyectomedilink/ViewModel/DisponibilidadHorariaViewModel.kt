package com.example.proyectomedilink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import com.example.proyectomedilink.repository.DisponibilidadHorariaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisponibilidadHorariaViewModel : ViewModel() {

    private val repository = DisponibilidadHorariaRepository()

    // LiveData privado para encapsulamiento
    private val _disponibilidadesHorarias = MutableLiveData<List<DisponibilidadHoraria>>(emptyList())
    val disponibilidadesHorarias: LiveData<List<DisponibilidadHoraria>> = _disponibilidadesHorarias

    // Campo para almacenar el ID del médico actual
    private var _medicoId: Long? = null
    val medicoId: Long?
        get() = _medicoId

    // Obtener la disponibilidad horaria de un médico
    fun obtenerDisponibilidad(medicoId: Long) {
        _medicoId = medicoId
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val lista = repository.obtenerDisponibilidad(medicoId)
                _disponibilidadesHorarias.postValue(lista)
            } catch (e: Exception) {
                e.printStackTrace()
                // Aquí podrías emitir un estado de error si deseas
            }
        }
    }

    // Guardar una nueva disponibilidad
    fun guardarDisponibilidadHoraria(disponibilidad: DisponibilidadHoraria) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.guardarDisponibilidadHoraria(disponibilidad)
            }
        }
    }

    // Eliminar una disponibilidad existente
    fun eliminarDisponibilidad(id: Long, medicoId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.eliminarDisponibilidad(id)
                obtenerDisponibilidad(medicoId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Actualizar una disponibilidad existente
    fun actualizarDisponibilidad(id: Long, disponibilidad: DisponibilidadHoraria) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.actualizarDisponibilidad(id, disponibilidad)
                obtenerDisponibilidad(disponibilidad.medicoId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Obtener una disponibilidad puntual desde el LiveData
    fun obtenerDisponibilidadPorId(id: Long): DisponibilidadHoraria? {
        return _disponibilidadesHorarias.value?.find { it.id == id }
    }
}
