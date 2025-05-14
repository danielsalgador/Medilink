package com.example.proyectomedilink.Model
import java.time.LocalDateTime

data class DisponibilidadHoraria(
    val id: Long,
    val fechaHora: LocalDateTime,
    val disponible: Boolean,
    val medicoId: Long
)