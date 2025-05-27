package com.example.proyectomedilink.Model
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.time.LocalDateTime

data class DisponibilidadHoraria(
    val id: Long,
    val fecha: LocalDate,
    val hora: LocalTime,
    val disponible: Boolean,
    val medicoId: Long
)