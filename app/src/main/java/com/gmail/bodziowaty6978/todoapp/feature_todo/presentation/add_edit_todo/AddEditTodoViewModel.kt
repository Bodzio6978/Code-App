package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val useCases: TodoUseCases
) : ViewModel() {

    private val _todoState = mutableStateOf<TodoTextFieldState>(
        TodoTextFieldState(
            hint = "Enter todo title..."
        )
    )
    val todoState: State<TodoTextFieldState> = _todoState

    private val _todoUiEvent = MutableSharedFlow<AddEditTodoUiEvent>()
    val todoUiEvent: SharedFlow<AddEditTodoUiEvent> = _todoUiEvent

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.EnteredTitle -> {
                _todoState.value = todoState.value.copy(
                    text = event.value
                )
            }
            is AddEditTodoEvent.ChangeTitleFocus -> {
                _todoState.value = todoState.value.copy(
                    isHintVisible = !event.focusState.isFocused && todoState.value.text.isBlank()
                )
            }
            is AddEditTodoEvent.SaveTodo -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = useCases.insertTodo(
                        Todo(
                            title = todoState.value.text,
                            userId = 1, //Current user id
                            id = 1
                        )
                    )
                    if (result.isSuccessful){
                        _todoUiEvent.emit(AddEditTodoUiEvent.SaveTodo)
                    }
                    Log.e("huj",result.toString())
                    _todoUiEvent.emit(AddEditTodoUiEvent.ShowSnackbar(result.message()))
                }

            }
        }
    }

}