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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectomedilink.viewmodel.MedicoViewModel
import androidx.compose.ui.layout.ContentScale
import com.example.proyectomedilink.component.StyledTextField

@Composable
fun EliminarMedicoScreen(viewModel: MedicoViewModel, navController: NavController) {
    var idInput by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1BqNcJUgenZMaZd4vRFgmP_1okebTLEIy"

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xCCF7F9F9))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            StyledTextField(
                value = idInput,
                placeholder = "ID del médico a eliminar",
                onValueChange = { input -> idInput = input.filter { char -> char.isDigit() } }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val id = idInput.toLongOrNull()
                    if (id != null && id > 0) {
                        viewModel.eliminarMedico(id)
                        mensaje = "Médico eliminado"
                        errorMessage = ""
                        idInput = ""
                    } else {
                        errorMessage = "Por favor ingrese un ID válido"
                        mensaje = ""
                    }
                },
                modifier = Modifier.align(Alignment.End),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Eliminar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (mensaje.isNotEmpty()) {
                Text(text = mensaje, color = MaterialTheme.colorScheme.primary)
            }
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}


