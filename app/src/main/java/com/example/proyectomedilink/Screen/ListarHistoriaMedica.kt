package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectomedilink.Model.HistoriaMedica
import com.example.proyectomedilink.viewmodel.HistoriaMedicaViewModel
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarHistoriaMedica(
    navController: NavHostController,
    viewModel: HistoriaMedicaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val historias by viewModel.historiasMedicas.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.obtenerHistorias()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de Historias Médicas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar_historia") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Historia")
            }
        }
    ) { padding ->
        if (historias.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay historias médicas registradas.")
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    top = padding.calculateTopPadding() + 8.dp,
                    bottom = padding.calculateBottomPadding() + 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                items(historias) { historia: HistoriaMedica ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("ID: ${historia.id}", style = MaterialTheme.typography.bodySmall)
                            Text("Diagnóstico: ${historia.diagnostico}", style = MaterialTheme.typography.bodyLarge)
                            Text("Tratamiento: ${historia.tratamiento}", style = MaterialTheme.typography.bodyMedium)
                            Text("Fecha: ${historia.fecha}", style = MaterialTheme.typography.bodyMedium)
                            Text("Paciente ID: ${historia.pacienteId}", style = MaterialTheme.typography.bodySmall)
                            Text("Médico ID: ${historia.medicoId}", style = MaterialTheme.typography.bodySmall)

                            Spacer(modifier = Modifier.height(8.dp))

                            Row {
                                TextButton(onClick = {
                                    navController.navigate("actualizar_historia/${historia.id}")
                                }) {
                                    Text("Editar")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                TextButton(onClick = {
                                    navController.navigate("eliminar_historia/${historia.id}")
                                }) {
                                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
*/