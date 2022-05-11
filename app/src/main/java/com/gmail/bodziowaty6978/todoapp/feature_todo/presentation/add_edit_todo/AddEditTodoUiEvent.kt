package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo

sealed class AddEditTodoUiEvent {
    data class ShowSnackbar(val message:String): AddEditTodoUiEvent()
    data class SaveTodo(val message:String): AddEditTodoUiEvent()
}