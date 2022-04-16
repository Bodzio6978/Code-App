package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)