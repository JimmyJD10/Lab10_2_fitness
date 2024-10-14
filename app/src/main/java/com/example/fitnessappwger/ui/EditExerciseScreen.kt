package com.example.fitnessappwger.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessappwger.data.Exercise

@Composable
fun EditExerciseScreen(
    exercise: Exercise,
    onSave: (Exercise) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(exercise.name) }
    var description by remember { mutableStateOf(exercise.description) }
    var category by remember { mutableStateOf(exercise.category ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Editar Ejercicio",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") }
        )

        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Categoría") }
        )

        Row(modifier = Modifier.padding(top = 16.dp)) {
            Button(
                onClick = {
                    val updatedExercise = exercise.copy(name = name, description = description, category = category)
                    onSave(updatedExercise)
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Guardar")
            }
            Button(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    }
}
