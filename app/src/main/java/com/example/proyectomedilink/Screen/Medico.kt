package com.example.proyectomedilink.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectomedilink.viewmodel.MedicoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicoScreen(navController: NavHostController, viewModel: MedicoViewModel) {
    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1Cs1UC69dCq93jfCJu3dISKN_l_de_PSk"

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
                .background(Color(0xAA000000))
        )

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Gestión de Médicos", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Opciones de Médico", color = Color.White)

                Spacer(modifier = Modifier.height(24.dp))

                // Opciones en forma de iconos
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1b-I3tLWD9q9VTREbc31F8E-tzQGswY63", // Agregar Médico
                        label = "Agregar Médico",
                        onClick = { navController.navigate("agregar_medico") }
                    )
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1Wz9RAEBzmumS7uHjiQlnvbrzauiQl7Yx", // Listar Médicos
                        label = "Listar Médicos",
                        onClick = { navController.navigate("listar_medicos") }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1GHqgkPUQwY_ZRPsDtZQr9BwT_fMKxRKD", // Actualizar Médico
                        label = "Actualizar Médico",
                        onClick = { navController.navigate("actualizar_medico") }
                    )
                    MedicoOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1hx1fb_TMNPvGrj4pKJFBoc4JLVopWYdX", // Eliminar Médico
                        label = "Eliminar Médico",
                        onClick = { navController.navigate("eliminar_medico") }
                    )
                }
            }
        }
    }
}

@Composable
fun MedicoOptionIcon(imageUrl: String, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = label,
            modifier = Modifier
                .size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}
