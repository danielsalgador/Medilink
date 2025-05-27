package com.example.proyectomedilink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectomedilink.viewmodel.MedicoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicoScreen(navController: NavController, viewModel: MedicoViewModel) {
    val backgroundImageUrl = "https://drive.google.com/uc?export=download&id=1WYWuGSzoQZpDW4P668zhGYfb6umltEhi"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Médicos") }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(start = 8.dp)
                ) {
                    Text("<-")
                }
            }
        }
    ) { paddingValues ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            // Imagen de fondo
            AsyncImage(
                model = backgroundImageUrl,
                contentDescription = "Imagen de fondo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Capa translúcida
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.75f))
            )

            // Contenido centrado
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Opciones de Médico",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { navController.navigate("agregar_medico") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Agregar Médico")
                }

                Button(
                    onClick = { navController.navigate("listar_medicos") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Listar Médicos")
                }

                Button(
                    onClick = { navController.navigate("actualizar_medico") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Actualizar Médico")
                }

                Button(
                    onClick = { navController.navigate("eliminar_medico") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Eliminar Médico")
                }
            }
        }
    }
}
