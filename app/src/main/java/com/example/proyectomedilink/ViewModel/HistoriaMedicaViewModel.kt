package com.example.proyectomedilink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomedilink.Model.HistoriaMedica
import com.example.proyectomedilink.repository.HistoriaMedicaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoriaMedicaViewModel : ViewModel() {
    private val repository = HistoriaMedicaRepository()

    private val _historiasMedicas = MutableLiveData<List<HistoriaMedica>>()
    val historiasMedicas: MutableLiveData<List<HistoriaMedica>> get() = _historiasMedicas

    private val _historiaMedica = MutableLiveData<HistoriaMedica>()
    val historiaMedica: LiveData<HistoriaMedica> get() = _historiaMedica

    /*fun obtenerHistorias() {
        viewModelScope.launch {
            val historias = withContext(Dispatchers.IO) {
                repository.obtenerHistorias()
            }
            _historiasMedicas.postValue(historias)
        }
    }

    fun obtenerHistoria(id: Long) {
        viewModelScope.launch {
            val historia = withContext(Dispatchers.IO) {
                repository.obtenerHistoria(id)
            }
            _historiaMedica.postValue(historia)
        }
    }

    fun guardarHistoria(historiaMedica: HistoriaMedica) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.guardarHistoria(historiaMedica)
            }
            obtenerHistorias()
        }
    }

    fun eliminarHistoria(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.eliminarHistoria(id)
            }
            obtenerHistorias()
        }
    }

    fun actualizarHistoria(id: Long, historiaMedica: HistoriaMedica) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.actualizarHistoria(id, historiaMedica)
            }
            obtenerHistorias()
        }
    }*/
}
