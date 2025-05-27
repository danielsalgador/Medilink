package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.interfaces.RetrofitClient

class PacienteRepository {

    // Obtener todos los pacientes desde la API
    suspend fun obtenerPacientes(): List<Paciente> {
        return try {
            RetrofitClient.apiService.obtenerPacientes()
        } catch (e: Exception) {
            emptyList() // Devuelve lista vacía en caso de error
        }
    }

    // Guardar un nuevo paciente
    suspend fun guardarPaciente(paciente: Paciente): Paciente {
        return RetrofitClient.apiService.guardarPaciente(paciente)
    }

    // Actualizar paciente por ID
    suspend fun actualizarPaciente(id: Long, paciente: Paciente): Paciente {
        return RetrofitClient.apiService.actualizarPaciente(id, paciente)
    }

    // Eliminar paciente por ID
    suspend fun eliminarPaciente(id: Long) {
        RetrofitClient.apiService.eliminarPaciente(id)
    }
}
