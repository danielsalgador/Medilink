package com.example.proyectomedilink.Model

data class Medico(
    val id: Long?, // ← debe ser nullable para el registro, pero obligatorio para actualizar
    val nombre: String,
    val especialidad: String,
    val correo: String,
    val telefono: String
)
