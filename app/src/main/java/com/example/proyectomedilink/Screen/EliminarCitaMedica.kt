package com.example.proyectomedilink.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectomedilink.Model.CitaMedica
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EliminarCitaMedicaScreen(
    citaId: Long,
    viewModel: CitaMedicaViewModel = viewModel(),
    navController: NavController
) {
    val citas by viewModel.citasMedicas.observeAsState(emptyList())
    val operationSuccess by viewModel.operationSuccess.observeAsState(null)
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null) // Suponiendo que lo tienes en VM

    val cita = citas.find { it.id == citaId }

    LaunchedEffect(operationSuccess) {
        if (operationSuccess == true) {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eliminar Cita Médica") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), contentAlignment = Alignment.Center) {

            when {
                isLoading -> {
                    CircularProgressIndicator()
                }
                cita == null -> {
                    Text("Cita no encontrada", style = MaterialTheme.typography.titleMedium)
                }
                else -> {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "¿Está seguro que desea eliminar la cita de ${cita.fecha} a las ${cita.hora}?",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        errorMessage?.let {
                            Text(text = it, color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Button(
                            onClick = { viewModel.eliminarCitaMedica(citaId) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
