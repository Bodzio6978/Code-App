package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.util

sealed class Screen(val route:String) {
    object TodoScreen:Screen("todos_screen")
    object AddEditTodoScreen:Screen("add_edit_todo_screen")
}