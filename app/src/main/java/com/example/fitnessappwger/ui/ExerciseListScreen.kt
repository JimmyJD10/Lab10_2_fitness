package com.example.fitnessappwger.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessappwger.data.Exercise
import com.example.fitnessappwger.viewmodel.ExerciseViewModel

@Composable
fun ExerciseListScreen(viewModel: ExerciseViewModel = viewModel()) {
    val exerciseList by viewModel.exerciseList.collectAsState()
    var editingExercise by remember { mutableStateOf<Exercise?>(null) }
    var addingExercise by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = { addingExercise = true },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text(text = "Agregar Ejercicio")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(exerciseList) { exercise ->
                ExerciseItem(
                    exercise = exercise,
                    onEditClick = { editingExercise = exercise },
                    onDeleteClick = { viewModel.deleteExercise(exercise) }
                )
            }
        }
    }

    // Mostrar formulario para agregar nuevo ejercicio
    if (addingExercise) {
        AlertDialog(
            onDismissRequest = { addingExercise = false },
            title = { Text("Agregar Ejercicio") },
            text = {
                AddExerciseForm(
                    onSubmit = { name, description, category ->
                        viewModel.addExercise(name, description, category)
                        addingExercise = false
                    },
                    onCancel = { addingExercise = false }
                )
            },
            confirmButton = {
                Button(onClick = { addingExercise = false }) {
                    Text("Cerrar")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

    // Mostrar pantalla de edición si hay un ejercicio para editar
    editingExercise?.let { exercise ->
        AlertDialog(
            onDismissRequest = { editingExercise = null },
            title = { Text("Editar Ejercicio") },
            text = {
                EditExerciseScreen(
                    exercise = exercise,
                    onSave = { updatedExercise ->
                        viewModel.updateExercise(updatedExercise)
                        editingExercise = null
                    },
                    onCancel = { editingExercise = null }
                )
            },
            confirmButton = {
                Button(onClick = { editingExercise = null }) {
                    Text("Cerrar")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


