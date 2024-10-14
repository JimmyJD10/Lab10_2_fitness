package com.example.fitnessappwger.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessappwger.data.Exercise

@Composable
fun ExerciseItem(
    exercise: Exercise,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = exercise.name, style = MaterialTheme.typography.titleLarge)
            Text(text = exercise.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = exercise.category ?: "Sin categoría", style = MaterialTheme.typography.bodySmall)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onEditClick) {
                    Text("Editar")
                }
                Button(onClick = onDeleteClick, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)) {
                    Text("Eliminar")
                }
            }
        }
    }
}
