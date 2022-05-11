package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.components.HintTextField
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.components.ToolbarSection
import com.gmail.bodziowaty6978.todoapp.ui.theme.LightGrey
import com.gmail.bodziowaty6978.todoapp.ui.theme.LightRed
import com.gmail.bodziowaty6978.todoapp.util.TAG
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditTodoScreen(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel(),
    todo:Todo? = null
) {
    val todoState = viewModel.todoState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.todoUiEvent.collectLatest { event ->
            when(event){
                is AddEditTodoUiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditTodoUiEvent.SaveTodo -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = true){
        if (todo!=null){
            Log.e(TAG,todo.toString())
            viewModel.onEvent(AddEditTodoEvent.EditingTodo(todo = todo))
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                // custom snackbar with the custom colors
                Snackbar(
                    backgroundColor = LightGrey,
                    content = {
                              Text(text = data.message,color = Color.White)
                    },
                    modifier = Modifier.padding(12.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTodoEvent.SaveTodo)
            },
                backgroundColor = LightRed
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save icon"
                )

            }
        }
    ) {

        Column(
            Modifier.fillMaxSize()
        ) {
            ToolbarSection(
                isTodoEdited = todoState.todoId!=-1,
                onBackIconPressed = {
                    navController.navigateUp()
                }
            )

            HintTextField(
                text = todoState.text,
                hint = todoState.hint,
                isHintVisible = todoState.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditTodoEvent.EnteredTitle(it)
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(
                        AddEditTodoEvent.ChangeTitleFocus(it)
                    )
                })

        }

    }


}