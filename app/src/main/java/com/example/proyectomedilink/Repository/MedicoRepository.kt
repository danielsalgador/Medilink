package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.interfaces.RetrofitClient

class MedicoRepository {

    // Obtener todos los médicos desde la API
    suspend fun obtenerMedicos(): List<Medico> {
        return try {
            RetrofitClient.apiService.obtenerMedicos()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Guardar un nuevo médico
    suspend fun guardarMedico(medico: Medico): Medico {
        return RetrofitClient.apiService.guardarMedico(medico)
    }

    // Actualizar médico por ID
    suspend fun actualizarMedico(id: Long, medico: Medico): Medico {
        return RetrofitClient.apiService.actualizarMedico(id, medico)
    }

    // Eliminar médico por ID
    suspend fun eliminarMedico(id: Long) {
        RetrofitClient.apiService.eliminarMedico(id)
    }
}
