package com.gmail.bodziowaty6978.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.AddEditTodoScreen
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.TodoScreen
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.util.Screen
import com.gmail.bodziowaty6978.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoScreen.route
                    ){
                        composable(
                            route = Screen.TodoScreen.route
                        ){
                            TodoScreen(
                                navController = navController
                            )
                        }

                        composable(
                            route = Screen.AddEditTodoScreen.route
                        ){
                            AddEditTodoScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
