package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.components.GreetingsSection
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.components.TodoSection
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.util.Screen
import com.gmail.bodziowaty6978.todoapp.ui.theme.LightRed
import com.gmail.bodziowaty6978.todoapp.util.toJson
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.getTodos()
    }

    LaunchedEffect(true) {
        viewModel.todoUiState.collectLatest { todoEvent ->
            when(todoEvent){
                is TodoUiEvent.CompletedTodo -> {
                    scaffoldState.snackbarHostState.showSnackbar("Completed todo")
                }

                is TodoUiEvent.EditTodo -> {
                    navController.navigate(Screen.AddEditTodoScreen.route + "?todo=${todoEvent.todo.toJson()}")
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditTodoScreen.route)
                },
                backgroundColor = LightRed
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add icon"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            GreetingsSection()
            TodoSection(
                todoItems = state.value.todos,
                onEvent = { todoEvent ->
                    viewModel.onEvent(todoEvent)
                }
            )

        }
    }
}