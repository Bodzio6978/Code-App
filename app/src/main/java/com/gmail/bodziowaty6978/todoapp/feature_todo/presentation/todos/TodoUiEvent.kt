package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo

sealed class TodoUiEvent{
    data class EditTodo(val todo:Todo):TodoUiEvent()
    data class Error(val message:String):TodoUiEvent()
    object CompletedTodo:TodoUiEvent()
}
