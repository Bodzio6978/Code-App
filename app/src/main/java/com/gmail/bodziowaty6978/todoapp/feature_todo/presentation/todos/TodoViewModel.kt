package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val useCases: TodoUseCases
): ViewModel(){

    private val _state = mutableStateOf<TodoState>(TodoState())
    val state: State<TodoState> = _state

    private val _todoUiState = MutableSharedFlow<TodoUiEvent>()
    val todoUiState: SharedFlow<TodoUiEvent> = _todoUiState

    private var job: Job? = null

    fun onEvent(todoEvent: TodoEvent){
        when(todoEvent){
            is TodoEvent.CompleteTodo -> {
                viewModelScope.launch(Dispatchers.IO) {
                    useCases.updateTodo(todoEvent.todo.copy(
                        completed = true
                    ))
                }
            }
            is TodoEvent.EditTodo -> {
                viewModelScope.launch {
                    _todoUiState.emit(
                        TodoUiEvent.EditTodo(todoEvent.todo)
                    )
                }
            }
        }

    }

    fun getTodos(){
        viewModelScope.launch (Dispatchers.IO){
            _state.value = state.value.copy(
                todos = useCases.getTodos()
            )
        }
    }
}