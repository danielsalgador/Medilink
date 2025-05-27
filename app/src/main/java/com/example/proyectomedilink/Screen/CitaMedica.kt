package com.example.proyectomedilink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitaMedicaScreen(
    navController: NavController,
    viewModel: CitaMedicaViewModel,
    citaId: Long
) {
    val backgroundImageUrl =
        "https://drive.google.com/uc?export=download&id=1KpLa7kyuMggq9tcJLcv3jPEYHano_kBG"

    val isValidId = citaId != 0L

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        AsyncImage(
            model = backgroundImageUrl,
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Capa semitransparente
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAA000000))
        )

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Gestión de Citas Médicas", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Opciones de Cita Médica", color = Color.White, style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(24.dp))

                // Primera fila de opciones
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Navega a Agregar Cita Médica
                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1b-I3tLWD9q9VTREbc31F8E-tzQGswY63",
                        label = "Agregar Cita Médica",
                        onClick = {
                            navController.navigate("agregar_cita_medica")
                        }
                    )
                    // Navega a Listar Citas Médicas
                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1Wz9RAEBzmumS7uHjiQlnvbrzauiQl7Yx",
                        label = "Listar Citas Médicas",
                        onClick = {
                            navController.navigate("listar_citas_medicas")
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Segunda fila de opciones
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Navega a Actualizar Cita Médica, pasando el ID (solo si es válido)
                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1GHqgkPUQwY_ZRPsDtZQr9BwT_fMKxRKD",
                        label = "Actualizar Cita Médica",
                        onClick = {
                            if (isValidId) navController.navigate("actualizar_cita_medica/$citaId")
                        }
                    )
                    // Navega a Eliminar Cita Médica, pasando el ID (solo si es válido)
                    CitaMedicaOptionIcon(
                        imageUrl = "https://drive.google.com/uc?export=download&id=1hx1fb_TMNPvGrj4pKJFBoc4JLVopWYdX",
                        label = "Eliminar Cita Médica",
                        onClick = {
                            if (isValidId) navController.navigate("eliminar_cita_medica/$citaId")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CitaMedicaOptionIcon(
    imageUrl: String,
    label: String,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(64.dp)
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

// Ejemplo básico de NavHost con navegación para las pantallas de cita médica

@Composable
fun CitaMedicaNavHost(viewModel: CitaMedicaViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu_cita_medica/{citaId}",
        builder = {
            composable(
                "menu_cita_medica/{citaId}",
                arguments = listOf(navArgument("citaId") {
                    type = NavType.LongType
                    defaultValue = 0L
                })
            ) { backStackEntry ->
                val citaId = backStackEntry.arguments?.getLong("citaId") ?: 0L
                CitaMedicaScreen(navController, viewModel, citaId)
            }

            composable("agregar_cita_medica") {
                // TODO: Pantalla para agregar cita médica
            }

            composable("listar_citas_medicas") {
                // TODO: Pantalla para listar citas médicas
            }

            composable(
                "actualizar_cita_medica/{citaId}",
                arguments = listOf(navArgument("citaId") { type = NavType.LongType })
            ) { backStackEntry ->
                val citaId = backStackEntry.arguments?.getLong("citaId") ?: 0L
                // TODO: Pantalla para actualizar cita médica con citaId
            }

            composable(
                "eliminar_cita_medica/{citaId}",
                arguments = listOf(navArgument("citaId") { type = NavType.LongType })
            ) { backStackEntry ->
                val citaId = backStackEntry.arguments?.getLong("citaId") ?: 0L
                // TODO: Pantalla para eliminar cita médica con citaId
            }
        }
    )
}
