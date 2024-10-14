package com.example.fitnessappwger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessappwger.RetrofitInstance
import com.example.fitnessappwger.data.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {
    private val _exerciseList = MutableStateFlow<List<Exercise>>(emptyList())
    val exerciseList: StateFlow<List<Exercise>> = _exerciseList

    init {
        fetchExercises()
    }

    // Fetch de ejercicios desde la API
    private fun fetchExercises() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getExercises()
            if (response.isSuccessful) {
                response.body()?.let {
                    _exerciseList.value = it.results
                } ?: run {
                    _exerciseList.value = emptyList()
                }
            } else {
                _exerciseList.value = emptyList()
            }
        }
    }

    // Agregar  ejercicio
    fun addExercise(name: String, description: String, category: String? = "General") {
        val newExercise = Exercise(id = _exerciseList.value.size + 1, name = name, description = description, category = category)
        _exerciseList.value = _exerciseList.value + newExercise
    }

    // Eliminar un ejercicio
    fun deleteExercise(exercise: Exercise) {
        _exerciseList.value = _exerciseList.value - exercise
    }

    // Actualizar un ejercicio
    fun updateExercise(updatedExercise: Exercise) {
        _exerciseList.value = _exerciseList.value.map { exercise ->
            if (exercise.id == updatedExercise.id) updatedExercise else exercise
        }
    }
}
