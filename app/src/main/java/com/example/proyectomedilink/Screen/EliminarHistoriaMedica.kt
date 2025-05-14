package com.example.proyectomedilink.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.proyectomedilink.viewmodel.HistoriaMedicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EliminarHistoriaMedicaScreen(
    navController: NavHostController,
    historiaId: Long,
    viewModel: HistoriaMedicaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var confirmado by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Eliminar Historia Médica") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(top = padding.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¿Deseas eliminar la historia con ID $historiaId?", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))

            if (!confirmado) {
                Row {
                    Button(onClick = {
                        confirmado = true
                        viewModel.eliminarHistoria(historiaId)
                        mensaje = "Historia eliminada"
                    }) {
                        Text("Sí, eliminar")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Text("Cancelar")
                    }
                }
            } else {
                mensaje?.let {
                    Text(it, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Button(onClick = { navController.navigateUp() }) {
                    Text("Volver al listado")
                }
            }
        }
    }
}
