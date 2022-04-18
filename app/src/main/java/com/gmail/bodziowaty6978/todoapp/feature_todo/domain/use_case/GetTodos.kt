package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository

class GetTodos(
    private val repository: TodoRepository
){

    suspend operator fun invoke():List<Todo>{
        return repository.getTodoItems()
    }
}