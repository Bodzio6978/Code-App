package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository
import retrofit2.Response

class DeleteTodo(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(id: Int): Response<Todo> {
        return repository.deleteTodoItem(id)
    }
}