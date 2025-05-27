package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.CitaMedica
import com.example.proyectomedilink.interfaces.RetrofitClient

class CitaMedicaRepository {

    suspend fun obtenerCitasMedicas(): List<CitaMedica> {
        return try {
            RetrofitClient.apiService.obtenerCitasMedicas()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun obtenerCitaPorId(id: Long): CitaMedica? {
        return try {
            RetrofitClient.apiService.obtenerCitaMedicaPorId(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun agregarCitaMedica(cita: CitaMedica): CitaMedica? {
        return try {
            RetrofitClient.apiService.guardarCitaMedica(cita)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun actualizarCita(id: Long, cita: CitaMedica): CitaMedica? {
        return try {
            RetrofitClient.apiService.actualizarCitaMedica(id, cita)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun eliminarCita(id: Long): Boolean {
        return try {
            RetrofitClient.apiService.eliminarCitaMedica(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}
