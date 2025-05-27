package com.example.proyectomedilink.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyectomedilink.Model.Medico
import com.example.proyectomedilink.Model.Paciente
import com.example.proyectomedilink.viewmodel.CitaMedicaViewModel
import androidx.compose.ui.text.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarCitaScreen(
    navController: NavController,
    viewModel: CitaMedicaViewModel = viewModel()
) {
    val pacientes by viewModel.pacientes.observeAsState(emptyList())
    val medicos   by viewModel.medicos.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMsg  by viewModel.errorMessage.observeAsState()
    val success   by viewModel.operationSuccess.observeAsState()

    var selPaciente by remember { mutableStateOf<Paciente?>(null) }
    var openPacMenu by remember { mutableStateOf(false) }

    var selMedico by remember { mutableStateOf<Medico?>(null) }
    var openMedMenu by remember { mutableStateOf(false) }

    var fecha by remember { mutableStateOf("") }
    var hora  by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    var motivo by remember { mutableStateOf("") }

    val estados = listOf("Programada", "Completada", "Cancelada")
    var estado    by remember { mutableStateOf(estados.first()) }
    var openEstMenu by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    val calendarFecha by remember { mutableStateOf(Calendar.getInstance()) }
    val calendarHora  by remember { mutableStateOf(Calendar.getInstance()) }

    val context = LocalContext.current

    LaunchedEffect(success) {
        if (success == true) navController.popBackStack()
    }

    LaunchedEffect(errorMsg) {
        errorMessage = errorMsg ?: ""
    }

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    // Fondo con imagen + capa translúcida
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://drive.google.com/uc?export=download&id=1fMMYqA7nMlT2XjpK46V3wDJDJeiyhesq",
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xCCF7F9F9))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Paciente (como TextField personalizado con Dropdown)
            MyStyledDropdownField(
                label = "Paciente",
                value = selPaciente?.nombre ?: "",
                expanded = openPacMenu,
                onExpandedChange = { openPacMenu = it },
                items = pacientes.map { it.nombre },
                onItemSelected = { index ->
                    selPaciente = pacientes[index]
                    openPacMenu = false
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Médico
            MyStyledDropdownField(
                label = "Médico",
                value = selMedico?.nombre ?: "",
                expanded = openMedMenu,
                onExpandedChange = { openMedMenu = it },
                items = medicos.map { it.nombre },
                onItemSelected = { index ->
                    selMedico = medicos[index]
                    openMedMenu = false
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Fecha y Hora
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MyStyledTextField(
                    value = fecha,
                    placeholder = "Fecha (YYYY-MM-DD)",
                    onValueChange = { fecha = it },
                    modifier = Modifier.weight(1f),
                    trailingContent = {
                        Text(
                            "📅",
                            modifier = Modifier
                                .clickable { showDatePicker = true }
                                .padding(8.dp)
                        )
                    }
                )
                MyStyledTextField(
                    value = hora,
                    placeholder = "Hora (HH:MM)",
                    onValueChange = { hora = it },
                    modifier = Modifier.weight(1f),
                    trailingContent = {
                        Text(
                            "⏰",
                            modifier = Modifier
                                .clickable { showTimePicker = true }
                                .padding(8.dp)
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (showDatePicker) {
                DatePickerDialog(
                    context,
                    { _: DatePicker, y, m, d ->
                        calendarFecha.set(y, m, d)
                        fecha = "%04d-%02d-%02d".format(y, m + 1, d)
                        showDatePicker = false
                    },
                    calendarFecha.get(Calendar.YEAR),
                    calendarFecha.get(Calendar.MONTH),
                    calendarFecha.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            if (showTimePicker) {
                TimePickerDialog(
                    context,
                    { _: TimePicker, h, min ->
                        calendarHora.set(Calendar.HOUR_OF_DAY, h)
                        calendarHora.set(Calendar.MINUTE, min)
                        hora = "%02d:%02d".format(h, min)
                        showTimePicker = false
                    },
                    calendarHora.get(Calendar.HOUR_OF_DAY),
                    calendarHora.get(Calendar.MINUTE),
                    true
                ).show()
            }

            // Motivo
            MyStyledTextField(value = motivo, placeholder = "Motivo", onValueChange = { motivo = it })
            Spacer(modifier = Modifier.height(16.dp))

            // Estado (Dropdown)
            MyStyledDropdownField(
                label = "Estado",
                value = estado,
                expanded = openEstMenu,
                onExpandedChange = { openEstMenu = it },
                items = estados,
                onItemSelected = { index ->
                    estado = estados[index]
                    openEstMenu = false
                }
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Botón Guardar
            Button(
                onClick = {
                    when {
                        selPaciente == null -> viewModel.setErrorMessage("Seleccione un paciente.")
                        selMedico   == null -> viewModel.setErrorMessage("Seleccione un médico.")
                        fecha.isBlank()     -> viewModel.setErrorMessage("Ingrese una fecha.")
                        hora.isBlank()      -> viewModel.setErrorMessage("Ingrese una hora.")
                        motivo.isBlank()    -> viewModel.setErrorMessage("Ingrese un motivo.")
                        else -> viewModel.agregarCitaMedica(
                            pacienteId = selPaciente!!.id!!,
                            medicoId  = selMedico!!.id!!,
                            motivo    = motivo,
                            estadoStr = estado,
                            fechaStr  = fecha,
                            horaStr   = hora
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Guardar Cita Médica")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun MyStyledTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    trailingContent: @Composable (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(30.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true,
        trailingIcon = trailingContent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStyledDropdownField(
    label: String,
    value: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    items: List<String>,
    onItemSelected: (index: Int) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(30.dp))
    ) {
        TextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onItemSelected(index)
                    }
                )
            }
        }
    }
}
