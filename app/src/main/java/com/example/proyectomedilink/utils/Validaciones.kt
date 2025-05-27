package com.example.proyectomedilink.utils

fun validarCamposCita(
    pacienteId: String,
    medicoId: String,
    fecha: String,
    hora: String,
    motivo: String,
    estado: String
): Boolean {
    return pacienteId.isNotBlank() && medicoId.isNotBlank() &&
            fecha.isNotBlank() && hora.isNotBlank() &&
            motivo.isNotBlank() && estado.isNotBlank() &&
            pacienteId.toLongOrNull() != null && medicoId.toLongOrNull() != null &&
            fecha.matches(Regex("\\d{4}-\\d{2}-\\d{2}")) &&
            hora.matches(Regex("\\d{2}:\\d{2}"))
}