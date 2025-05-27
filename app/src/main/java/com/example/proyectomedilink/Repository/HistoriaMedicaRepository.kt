package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.HistoriaMedica
import com.example.proyectomedilink.interfaces.RetrofitClient

class HistoriaMedicaRepository {

    /* Obtener todas las historias médicas
    suspend fun obtenerHistorias(): List<HistoriaMedica> {
        return RetrofitClient.apiService.obtenerHistorias()
    } */

    // Obtener una historia médica por ID
    suspend fun obtenerHistoria(id: Long): HistoriaMedica {
        return RetrofitClient.apiService.obtenerHistoriaMedica(id)
    }

    // Guardar una nueva historia médica
    suspend fun guardarHistoria(historiaMedica: HistoriaMedica): HistoriaMedica {
        return RetrofitClient.apiService.guardarHistoriaMedica(historiaMedica)
    }

    // Actualizar una historia médica existente
    suspend fun actualizarHistoria(id: Long, historiaMedica: HistoriaMedica): HistoriaMedica {
        return RetrofitClient.apiService.actualizarHistoriaMedica(id, historiaMedica)
    }

    // Eliminar una historia médica por ID
    suspend fun eliminarHistoria(id: Long) {
        RetrofitClient.apiService.eliminarHistoriaMedica(id)
    }
}
