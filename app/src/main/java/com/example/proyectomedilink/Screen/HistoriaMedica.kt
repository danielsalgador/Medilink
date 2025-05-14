package com.example.proyectomedilink.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectomedilink.Model.HistoriaMedica
import com.example.proyectomedilink.viewmodel.HistoriaMedicaViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoriaMedicaScreen(navController: NavHostController,
    historiaId: Long,
    viewModel: HistoriaMedicaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val historia by viewModel.historiaMedica.observeAsState()

    LaunchedEffect(historiaId) {
        viewModel.obtenerHistoria(historiaId)
    }

    historia?.let { h ->
        val backgroundImageUrl = "https://example.com/imagen_de_fondo" // Cambia esto a la URL de tu imagen de fondo

        Box(modifier = Modifier.fillMaxSize()) {
            // Imagen de fondo
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
                // Barra superior
                TopAppBar(
                    title = { Text("Historia Médica #${h.id}", color = Color.White) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Opciones de Historia Médica", color = Color.White)

                    Spacer(modifier = Modifier.height(24.dp))

                    // Opciones en forma de iconos
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HistoriaMedicaOptionIcon(
                            imageUrl = "https://example.com/editar_icono", // Cambia esto con la URL de tu icono
                            label = "Editar Historia Médica",
                            onClick = { navController.navigate("actualizar_historia/${h.id}") }
                        )
                        HistoriaMedicaOptionIcon(
                            imageUrl = "https://example.com/eliminar_icono", // Cambia esto con la URL de tu icono
                            label = "Eliminar Historia Médica",
                            onClick = { navController.navigate("eliminar_historia/${h.id}") }
                        )
                    }
                }
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun HistoriaMedicaOptionIcon(imageUrl: String, label: String, onClick: () -> Unit) {
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
