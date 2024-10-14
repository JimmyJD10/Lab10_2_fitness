package com.example.fitnessappwger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fitnessappwger.ui.ExerciseListScreen
import com.example.fitnessappwger.ui.theme.FitnessAppWgerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppWgerTheme {
                ExerciseListScreen()
            }
        }
    }
}
