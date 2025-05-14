package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.CitaMedica
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.interfaces.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CitaMedicaRepository {

    suspend fun obtenerCitas(): List<CitaMedica> = withContext(Dispatchers.IO) {
        try {
            RetrofitClient.apiService.obtenerTodasCitas()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun obtenerMedicos(): List<Medico> = withContext(Dispatchers.IO) {
        try {
            RetrofitClient.apiService.obtenerMedicos()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun obtenerPacientes(): List<Paciente> = withContext(Dispatchers.IO) {
        try {
            RetrofitClient.apiService.obtenerPacientes()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun guardarCita(cita: CitaMedica): Boolean = withContext(Dispatchers.IO) {
        try {
            // Para nuevas citas (id = null)
            RetrofitClient.apiService.guardarCitaMedica(cita)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun actualizarCita(cita: CitaMedica): Boolean = withContext(Dispatchers.IO) {
        try {
            // Verificamos que el ID no sea nulo para actualización
            cita.id?.let { id ->
                RetrofitClient.apiService.actualizarCitaMedica(id, cita)
                true
            } ?: run {
                // Si el ID es nulo, no podemos actualizar
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun eliminarCita(id: Long): Boolean = withContext(Dispatchers.IO) {
        try {
            RetrofitClient.apiService.eliminarCitaMedica(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}