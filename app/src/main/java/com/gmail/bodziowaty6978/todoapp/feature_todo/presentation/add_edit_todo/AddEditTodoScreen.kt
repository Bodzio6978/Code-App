package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.components.HintTextField
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.components.ToolbarSection
import com.gmail.bodziowaty6978.todoapp.ui.theme.LightRed
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditTodoScreen(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val titleState = viewModel.todoState
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
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
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
                false,
                onBackIconPressed = {
                    navController.navigateUp()
                }
            )

            HintTextField(
                text = titleState.value.text,
                hint = titleState.value.hint,
                isHintVisible = titleState.value.isHintVisible,
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