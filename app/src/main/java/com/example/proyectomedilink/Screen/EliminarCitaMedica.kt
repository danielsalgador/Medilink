package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel
import kotlinx.coroutines.launch

@Composable
fun EliminarCitaMedicaScreen(
    viewModel: CitaMedicaViewModel,
    navController: NavController,
    citaId: Long
) {
    val scope = rememberCoroutineScope()
    val errorMessage by viewModel.errorMessage.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val operationSuccess by viewModel.operationSuccess.observeAsState()

    // Show loading indicator while deleting
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // Automatically navigate back if deletion was successful
    LaunchedEffect(operationSuccess) {
        if (operationSuccess == true) {
            navController.popBackStack()
        }
    }

    AlertDialog(
        onDismissRequest = { navController.popBackStack() },
        title = { Text(text = "Confirmar Eliminación") },
        text = {
            Column {
                Text("¿Está seguro que desea eliminar la cita médica?")
                Spacer(modifier = Modifier.height(8.dp))
                if (!errorMessage.isNullOrEmpty()) {
                    Text(
                        text = errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    scope.launch {
                        viewModel.eliminarCita(citaId)
                    }
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { navController.popBackStack() }
            ) {
                Text("Cancelar")
            }
        }
    )
}