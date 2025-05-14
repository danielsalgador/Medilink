package com.example.proyectomedilink.Model

import kotlinx.datetime.LocalDateTime

data class CitaMedica(
    val id: Long? = null,  // Nullable para nuevas citas
    val pacienteId: Long,   // ID obligatorio
    val medicoId: Long,     // ID obligatorio
    val fechaHora: LocalDateTime,
    val motivo: String,
    val estado: String      // Ej: "Programada", "Completada", "Cancelada"
) {
    // Propiedades calculadas
    val fecha: String
        get() = "${fechaHora.year}-${fechaHora.monthNumber.toString().padStart(2, '0')}-${fechaHora.dayOfMonth.toString().padStart(2, '0')}"

    val hora: String
        get() = "${fechaHora.hour.toString().padStart(2, '0')}:${fechaHora.minute.toString().padStart(2, '0')}"
}