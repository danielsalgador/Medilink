package com.example.proyectomedilink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomedilink.Model.CitaMedica
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.repository.CitaMedicaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import java.time.format.DateTimeFormatter

class CitaMedicaViewModel : ViewModel() {

    private val repository = CitaMedicaRepository()

    // Estados observables
    private val _citas = MutableLiveData<List<CitaMedica>>(emptyList())
    val citas: LiveData<List<CitaMedica>> get() = _citas

    private val _medicos = MutableLiveData<List<Medico>>(emptyList())
    val medicos: LiveData<List<Medico>> get() = _medicos

    private val _pacientes = MutableLiveData<List<Paciente>>(emptyList())
    val pacientes: LiveData<List<Paciente>> get() = _pacientes

    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _operationSuccess = MutableLiveData<Boolean?>(null)
    val operationSuccess: LiveData<Boolean?> get() = _operationSuccess

    // Método público para establecer mensajes de error
    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    init {
        cargarDatosIniciales()
    }

    private fun cargarDatosIniciales() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val medicosDeferred = async { repository.obtenerMedicos() }
                val pacientesDeferred = async { repository.obtenerPacientes() }

                _medicos.value = medicosDeferred.await()
                _pacientes.value = pacientesDeferred.await()
                obtenerCitas()
            } catch (e: Exception) {
                setErrorMessage("Error al cargar datos: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun obtenerCitas() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val listaCitas = withContext(Dispatchers.IO) { repository.obtenerCitas() }
                _citas.value = listaCitas
            } catch (e: Exception) {
                setErrorMessage("Error al obtener citas: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun agregarCitaMedica(
        pacienteId: Long,
        medicoId: Long,
        motivo: String,
        estado: String,
        fecha: String,
        hora: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _operationSuccess.value = null
            try {
                val fechaHora = parseFechaHora(fecha, hora)
                val nuevaCita = CitaMedica(
                    id = 0, // ID será generado por el backend
                    pacienteId = pacienteId,
                    medicoId = medicoId,
                    fechaHora = fechaHora,
                    motivo = motivo,
                    estado = estado
                )

                val resultado = withContext(Dispatchers.IO) {
                    repository.guardarCita(nuevaCita)
                }

                _operationSuccess.value = resultado
                if (resultado) {
                    obtenerCitas()
                } else {
                    setErrorMessage("Error al guardar la cita")
                }
            } catch (e: Exception) {
                setErrorMessage("Error: ${e.message}")
                _operationSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun actualizarCitaMedica(
        id: Long,
        pacienteId: Long,
        medicoId: Long,
        motivo: String,
        estado: String,
        fecha: String,
        hora: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _operationSuccess.value = null
            try {
                val fechaHora = parseFechaHora(fecha, hora)
                val citaActualizada = CitaMedica(
                    id = id,
                    pacienteId = pacienteId,
                    medicoId = medicoId,
                    fechaHora = fechaHora,
                    motivo = motivo,
                    estado = estado
                )

                val resultado = withContext(Dispatchers.IO) {
                    repository.actualizarCita(citaActualizada)
                }

                _operationSuccess.value = resultado
                if (resultado) {
                    obtenerCitas()
                } else {
                    setErrorMessage("Error al actualizar la cita")
                }
            } catch (e: Exception) {
                setErrorMessage("Error al actualizar: ${e.message}")
                _operationSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun eliminarCita(id: Long): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                _isLoading.postValue(true)
                _operationSuccess.postValue(null)
                val resultado = repository.eliminarCita(id)
                _operationSuccess.postValue(resultado)
                if (resultado) {
                    obtenerCitas()
                }
                resultado
            } catch (e: Exception) {
                _errorMessage.postValue("Error al eliminar: ${e.message}")
                _operationSuccess.postValue(false)
                false
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun resetOperationState() {
        _operationSuccess.value = null
        _errorMessage.value = ""
    }

    private fun parseFechaHora(fecha: String, hora: String): LocalDateTime {
        val fechaHoraString = "$fecha $hora"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val fechaHoraJava = java.time.LocalDateTime.parse(fechaHoraString, formatter)

        return LocalDateTime(
            fechaHoraJava.year,
            fechaHoraJava.monthValue,
            fechaHoraJava.dayOfMonth,
            fechaHoraJava.hour,
            fechaHoraJava.minute
        )
    }
}