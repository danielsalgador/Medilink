package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.interfaces.RetrofitClient

class PacienteRepository {

    suspend fun obtenerPacientes(): List<Paciente> {
        return RetrofitClient.apiService.obtenerPacientes()
    }

    suspend fun guardarPaciente(paciente: Paciente): Paciente {
        return RetrofitClient.apiService.guardarPaciente(paciente)
    }

    suspend fun actualizarPaciente(id: Long, paciente: Paciente): Paciente {
        return RetrofitClient.apiService.actualizarPaciente(id, paciente)
    }

    suspend fun eliminarPaciente(id: Long) {
        RetrofitClient.apiService.eliminarPaciente(id)
    }
}
