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

    // LiveData privado para evitar modificaciones directas
    private val _disponibilidadesHorarias = MutableLiveData<List<DisponibilidadHoraria>>(emptyList())

    // LiveData público para que otros componentes lo puedan observar
    val disponibilidadesHorarias: LiveData<List<DisponibilidadHoraria>> = _disponibilidadesHorarias

    // Agregar el campo medicoId
    private var _medicoId: Long? = null

    val medicoId: Long?
        get() = _medicoId

    // Método para obtener la disponibilidad horaria de un médico
    fun obtenerDisponibilidad(medicoId: Long) {
        _medicoId = medicoId
        viewModelScope.launch {
            val lista = withContext(Dispatchers.IO) {
                repository.obtenerDisponibilidad(medicoId)
            }
            _disponibilidadesHorarias.postValue(lista)
        }
    }

    // Método para guardar una nueva disponibilidad
    fun guardarDisponibilidad(disponibilidad: DisponibilidadHoraria) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.guardarDisponibilidad(disponibilidad)
            }
            obtenerDisponibilidad(disponibilidad.medicoId) // Recargar disponibilidades
        }
    }

    // Método para eliminar una disponibilidad
    fun eliminarDisponibilidad(id: Long, medicoId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.eliminarDisponibilidad(id)
            }
            obtenerDisponibilidad(medicoId) // Recargar disponibilidades
        }
    }

    // Método para actualizar la disponibilidad
    fun actualizarDisponibilidad(id: Long, disponibilidad: DisponibilidadHoraria) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.actualizarDisponibilidad(id, disponibilidad)
            }
            obtenerDisponibilidad(disponibilidad.medicoId) // Recargar disponibilidades
        }
    }

    // Método para obtener una disponibilidad por ID
    fun obtenerDisponibilidadPorId(id: Long): DisponibilidadHoraria? {
        return _disponibilidadesHorarias.value?.find { it.id == id }
    }
}
