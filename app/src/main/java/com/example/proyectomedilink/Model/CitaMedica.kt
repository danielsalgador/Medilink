package com.example.proyectomedilink.Model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class CitaMedica(
    val id: Long,
    val pacienteId: Long,
    val medicoId: Long,
    val motivo: String,
    val estado: EstadoCita,
    val fecha: LocalDate,
    val hora: LocalTime
) {
    val fechaFormateada: String
        get() = "${fecha.year}-${fecha.monthNumber.toString().padStart(2, '0')}-${fecha.dayOfMonth.toString().padStart(2, '0')}"

    val horaFormateada: String
        get() = "${hora.hour.toString().padStart(2, '0')}:${hora.minute.toString().padStart(2, '0')}"
}
