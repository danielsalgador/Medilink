package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.DisponibilidadHoraria
import com.example.proyectomedilink.interfaces.RetrofitClient

class DisponibilidadHorariaRepository {

    // Obtener la disponibilidad horaria de un médico
    suspend fun obtenerDisponibilidad(medicoId: Long): List<DisponibilidadHoraria> {
        return RetrofitClient.apiService.obtenerDisponibilidadHoraria(medicoId)
    }

    // Guardar la disponibilidad horaria de un médico
    suspend fun guardarDisponibilidadHoraria(disponibilidad: DisponibilidadHoraria): DisponibilidadHoraria {
        return RetrofitClient.apiService.guardarDisponibilidadHoraria(disponibilidad)
    }

    // Eliminar la disponibilidad horaria de un médico por ID
    suspend fun eliminarDisponibilidad(id: Long) {
        RetrofitClient.apiService.eliminarDisponibilidadHoraria(id)
    }

    // Actualizar la disponibilidad horaria de un médico por ID
    suspend fun actualizarDisponibilidad(id: Long, disponibilidad: DisponibilidadHoraria): DisponibilidadHoraria {
        return RetrofitClient.apiService.actualizarDisponibilidadHoraria(id, disponibilidad)
    }
}
