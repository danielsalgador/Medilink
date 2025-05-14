package com.example.proyectomedilink.repository

import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.interfaces.RetrofitClient

class MedicoRepository {

    suspend fun obtenerMedicos(): List<Medico> {
        return RetrofitClient.apiService.obtenerMedicos()
    }

    suspend fun guardarMedico(medico: Medico): Medico {
        return RetrofitClient.apiService.guardarMedico(medico)
    }

    suspend fun actualizarMedico(id: Long, medico: Medico): Medico {
        return RetrofitClient.apiService.actualizarMedico(id, medico)
    }

    suspend fun eliminarMedico(id: Long) {
        RetrofitClient.apiService.eliminarMedico(id)
    }
}
