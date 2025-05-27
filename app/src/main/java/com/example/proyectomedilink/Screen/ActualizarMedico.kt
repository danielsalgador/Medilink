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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.component.StyledTextField
import com.example.proyectomedilink.viewmodel.MedicoViewModel

@Composable
fun ActualizarMedicoScreen(viewModel: MedicoViewModel, navController: NavController) {
    var idInput by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var especialidad by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    var mensaje by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1Cs1UC69dCq93jfCJu3dISKN_l_de_PSk"

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Capa translúcida
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xCCF7F9F9))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Actualizar Médico",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            StyledTextField(value = idInput, placeholder = "ID del médico", onValueChange = { idInput = it })
            StyledTextField(value = nombre, placeholder = "Nuevo nombre", onValueChange = { nombre = it })
            StyledTextField(value = especialidad, placeholder = "Nueva especialidad", onValueChange = { especialidad = it })
            StyledTextField(value = correo, placeholder = "Nuevo correo", onValueChange = { correo = it })
            StyledTextField(value = telefono, placeholder = "Nuevo teléfono", onValueChange = { telefono = it })

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val id = idInput.toLongOrNull()
                    if (id != null && id > 0) {
                        val medicoActualizado = Medico(
                            id = id,
                            nombre = nombre,
                            especialidad = especialidad,
                            correo = correo,
                            telefono = telefono
                        )
                        viewModel.actualizarMedico(medicoActualizado)
                        mensaje = "✅ Médico actualizado exitosamente"
                        errorMessage = ""
                    } else {
                        mensaje = ""
                        errorMessage = "❌ Por favor ingrese un ID válido"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Actualizar", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (mensaje.isNotEmpty()) {
                Text(text = mensaje, color = Color(0xFF2E7D32), fontWeight = FontWeight.Medium)
            }

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color(0xFFD32F2F), fontWeight = FontWeight.Medium)
            }
        }
    }
}
