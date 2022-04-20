package com.gmail.bodziowaty6978.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.AddEditTodoScreen
import com.gmail.bodziowaty6978.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                AddEditTodoScreen()
            }
        }
    }
}
