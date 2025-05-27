import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectomedilink.Model.DisponibilidadHoraria
import com.example.proyectomedilink.viewmodel.DisponibilidadHorariaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarDisponibilidadHorariaScreen(
    medicoId: Long,
    viewModel: DisponibilidadHorariaViewModel,
    onEditar: (DisponibilidadHoraria) -> Unit,
    onEliminar: (DisponibilidadHoraria) -> Unit
) {
    val disponibilidades by viewModel.disponibilidadesHorarias.observeAsState(emptyList())

    LaunchedEffect(medicoId) {
        viewModel.obtenerDisponibilidad(medicoId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Disponibilidades Horarias") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (disponibilidades.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay disponibilidades registradas.")
                }
            } else {
                disponibilidades.forEach { disponibilidad ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable { onEditar(disponibilidad) },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            val fechaHoraFormateada = "${disponibilidad.fecha} ${disponibilidad.hora}"
                            Text("Fecha y Hora: $fechaHoraFormateada")
                            Text("Disponible: ${if (disponibilidad.disponible) "Sí" else "No"}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = { onEditar(disponibilidad) }) {
                                    Text("Editar")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                TextButton(onClick = { onEliminar(disponibilidad) }) {
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
