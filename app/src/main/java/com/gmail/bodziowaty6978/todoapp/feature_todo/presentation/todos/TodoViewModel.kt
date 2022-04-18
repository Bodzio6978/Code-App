package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case.TodoUseCases
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.util.TodoEvent
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.util.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val useCases: TodoUseCases
): ViewModel(){

    private val _state = mutableStateOf<TodoState>(TodoState())
    val state: State<TodoState> = _state

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