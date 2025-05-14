package com.example.proyectomedilink.Model

import java.time.LocalDate

data class HistoriaMedica(
    val id: Long,
    val diagnostico: String,
    val tratamiento: String,
    val fecha: LocalDate,
    val pacienteId: Long,
    val medicoId: Long
)