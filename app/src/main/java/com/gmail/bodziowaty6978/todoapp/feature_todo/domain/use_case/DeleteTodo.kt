package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository

class DeleteTodo(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(id: Int){
        repository.deleteTodoItem(id)
    }
}