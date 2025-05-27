package com.example.proyectomedilink.viewmodel

import androidx.lifecycle.*
import com.example.proyectomedilink.Model.*
import com.example.proyectomedilink.repository.CitaMedicaRepository
import com.example.proyectomedilink.repository.MedicoRepository
import com.example.proyectomedilink.repository.PacienteRepository
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class CitaMedicaViewModel : ViewModel() {

    private val repository = CitaMedicaRepository()
    private val pacienteRepository = PacienteRepository()
    private val medicoRepository = MedicoRepository()

    private val _pacientes = MutableLiveData<List<Paciente>>()
    val pacientes: LiveData<List<Paciente>> = _pacientes

    private val _medicos = MutableLiveData<List<Medico>>()
    val medicos: LiveData<List<Medico>> = _medicos

    private val _citasMedicas = MutableLiveData<List<CitaMedica>>()
    val citasMedicas: LiveData<List<CitaMedica>> = _citasMedicas

    private val _operationSuccess = MutableLiveData<Boolean?>()
    val operationSuccess: LiveData<Boolean?> = _operationSuccess

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        obtenerCitasMedicas()
        cargarDatos()
    }

    fun obtenerCitasMedicas() {
        viewModelScope.launch {
            val resultado = repository.obtenerCitasMedicas()
            _citasMedicas.value = resultado
        }
    }

    fun cargarDatos() {
        viewModelScope.launch {
            _pacientes.value = pacienteRepository.obtenerPacientes()
            _medicos.value = medicoRepository.obtenerMedicos()
        }
    }

    suspend fun obtenerCitaPorId(citaId: Long): CitaMedica? {
        return try {
            repository.obtenerCitaPorId(citaId)
        } catch (e: Exception) {
            _errorMessage.postValue("Error al obtener cita: ${e.message}")
            null
        }
    }

    fun agregarCitaMedica(
        pacienteId: Long,
        medicoId: Long,
        motivo: String,
        estadoStr: String,
        fechaStr: String,
        horaStr: String
    ) {
        viewModelScope.launch {
            _operationSuccess.value = null
            _errorMessage.value = null

            val fechaParsed = try {
                LocalDate.parse(fechaStr)
            } catch (e: Exception) {
                _errorMessage.value = "Formato de fecha inválido"
                _operationSuccess.value = false
                return@launch
            }

            val horaParsed = try {
                LocalTime.parse(horaStr)
            } catch (e: Exception) {
                _errorMessage.value = "Formato de hora inválido"
                _operationSuccess.value = false
                return@launch
            }

            val estadoParsed = try {
                EstadoCita.values().firstOrNull {
                    it.name.equals(estadoStr.trim(), ignoreCase = true)
                } ?: throw IllegalArgumentException("Estado inválido")
            } catch (e: Exception) {
                _errorMessage.value = "Estado inválido"
                _operationSuccess.value = false
                return@launch
            }

            try {
                val nuevaCita = CitaMedica(
                    id = 0L,
                    pacienteId = pacienteId,
                    medicoId = medicoId,
                    motivo = motivo,
                    estado = estadoParsed,
                    fecha = fechaParsed,
                    hora = horaParsed
                )
                val resultado = repository.agregarCitaMedica(nuevaCita)
                if (resultado != null) {
                    _operationSuccess.value = true
                    obtenerCitasMedicas()
                } else {
                    _errorMessage.value = "Error al agregar cita"
                    _operationSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al agregar cita: ${e.message}"
                _operationSuccess.value = false
            }
        }
    }

    fun actualizarCitaMedica(
        id: Long,
        pacienteId: Long,
        medicoId: Long,
        motivo: String,
        estadoStr: String,
        fechaStr: String,
        horaStr: String
    ) {
        viewModelScope.launch {
            _operationSuccess.value = null
            _errorMessage.value = null

            val fechaParsed = try {
                LocalDate.parse(fechaStr)
            } catch (e: Exception) {
                _errorMessage.value = "Formato de fecha inválido"
                _operationSuccess.value = false
                return@launch
            }

            val horaParsed = try {
                LocalTime.parse(horaStr)
            } catch (e: Exception) {
                _errorMessage.value = "Formato de hora inválido"
                _operationSuccess.value = false
                return@launch
            }

            val estadoParsed = try {
                EstadoCita.values().firstOrNull {
                    it.name.equals(estadoStr.trim(), ignoreCase = true)
                } ?: throw IllegalArgumentException("Estado inválido")
            } catch (e: Exception) {
                _errorMessage.value = "Estado inválido"
                _operationSuccess.value = false
                return@launch
            }

            try {
                val citaActualizada = CitaMedica(
                    id = id,
                    pacienteId = pacienteId,
                    medicoId = medicoId,
                    motivo = motivo,
                    estado = estadoParsed,
                    fecha = fechaParsed,
                    hora = horaParsed
                )
                val resultado = repository.actualizarCita(id, citaActualizada)
                if (resultado != null) {
                    _operationSuccess.value = true
                    obtenerCitasMedicas()
                } else {
                    _errorMessage.value = "Error al actualizar cita"
                    _operationSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar cita: ${e.message}"
                _operationSuccess.value = false
            }
        }
    }

    fun eliminarCitaMedica(id: Long) {
        viewModelScope.launch {
            _operationSuccess.value = null
            _errorMessage.value = null

            try {
                val eliminado = repository.eliminarCita(id)
                if (eliminado) {
                    _operationSuccess.value = true
                    obtenerCitasMedicas()
                } else {
                    _errorMessage.value = "Error al eliminar cita"
                    _operationSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar cita: ${e.message}"
                _operationSuccess.value = false
            }
        }
    }

    fun setErrorMessage(msg: String) {
        _errorMessage.value = msg
    }
}
