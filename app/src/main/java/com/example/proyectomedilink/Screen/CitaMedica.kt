package com.example.proyectomedilink.Screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitaMedicaScreen(navController: NavHostController, viewModel: CitaMedicaViewModel, citaId: Long) {
    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1KpLa7kyuMggq9tcJLcv3jPEYHano_kBG"

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
                .background(Color(0xAA000000)) // Fondo oscuro semi-transparente
        )

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Gestión de Citas Médicas", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Opciones de Cita Médica", color = Color.White)

                Spacer(modifier = Modifier.height(24.dp))

                // Opciones en forma de iconos
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1b-I3tLWD9q9VTREbc31F8E-tzQGswY63", // Agregar Cita Médica
                        label = "Agregar Cita Médica",
                        onClick = { navController.navigate("agregar_cita_medica") }
                    )
                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1Wz9RAEBzmumS7uHjiQlnvbrzauiQl7Yx", // Listar Citas Médicas
                        label = "Listar Citas Médicas",
                        onClick = { navController.navigate("listar_citas_medicas") }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1GHqgkPUQwY_ZRPsDtZQr9BwT_fMKxRKD", // Actualizar Cita Médica
                        label = "Actualizar Cita Médica",
                        onClick = { navController.navigate("actualizar_cita_medica/${citaId.toString()}") }                    )

                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1hx1fb_TMNPvGrj4pKJFBoc4JLVopWYdX", // Eliminar Cita Médica
                        label = "Eliminar Cita Médica",
                        onClick = { navController.navigate("eliminar_cita_medica/${citaId.toString()}") }
                    )
                }
            }
        }
    }
}

@Composable
fun CitaMedicaOptionIcon(imageUrl: String, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(64.dp) // Tamaño del icono
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = label,
                modifier = Modifier.size(48.dp)
            )
        }
        Text(text = label, color = Color.White, style = MaterialTheme.typography.bodyMedium)
    }
}
