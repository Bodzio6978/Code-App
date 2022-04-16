package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case

data class TodoUseCases (
    val deleteTodo:DeleteTodo,
    val insertTodo: InsertTodo,
    val getTodos: GetTodos,
    val getTodoById: GetTodoById,
    val updateTodo: UpdateTodo
)