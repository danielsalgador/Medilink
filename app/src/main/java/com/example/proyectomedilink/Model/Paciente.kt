package com.example.proyectomedilink.Model

// Clase Paciente
data class Paciente(
    val id: Long?, // Cambiar el tipo de Long a Long? para permitir valores nulos
    val nombre: String,
    val documento: String,
    val correo: String,
    val telefono: String,
    val direccion: String,
    val condicion: String
)
