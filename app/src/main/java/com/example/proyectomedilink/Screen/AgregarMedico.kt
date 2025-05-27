package com.example.proyectomedilink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.viewmodel.MedicoViewModel

@Composable
fun AgregarMedicoScreen(
    navController: NavHostController,
    medicoViewModel: MedicoViewModel
) {
    val backgroundImageUrl =
        "https://drive.google.com/uc?export=download&id=1fMMYqA7nMlT2XjpK46V3wDJDJeiyhesq"

    var nombre by remember { mutableStateOf("") }
    var especialidad by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xCCF7F9F9)) // Capa translúcida clara
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyStyledTextField(value = nombre, placeholder = "Nombre del Médico", onValueChange = { nombre = it })
            Spacer(modifier = Modifier.height(16.dp))

            MyStyledTextField(value = especialidad, placeholder = "Especialidad", onValueChange = { especialidad = it })
            Spacer(modifier = Modifier.height(16.dp))

            MyStyledTextField(value = correo, placeholder = "📧 Correo electrónico", onValueChange = { correo = it })
            Spacer(modifier = Modifier.height(16.dp))

            MyStyledTextField(value = telefono, placeholder = "📞 Teléfono", onValueChange = { telefono = it })
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank() && especialidad.isNotBlank() && correo.isNotBlank() && telefono.isNotBlank()) {
                        val nuevoMedico = Medico(
                            id = null,
                            nombre = nombre.trim(),
                            especialidad = especialidad.trim(),
                            correo = correo.trim(),
                            telefono = telefono.trim()
                        )
                        medicoViewModel.guardarMedico(nuevoMedico)
                        navController.navigate("medico_screen") {
                            popUpTo("medico_screen") { inclusive = true }
                        }
                    } else {
                        errorMessage = "Por favor, complete todos los campos."
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Guardar Médico")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun MyStyledTextField(value: String, placeholder: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(30.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true
    )
}
