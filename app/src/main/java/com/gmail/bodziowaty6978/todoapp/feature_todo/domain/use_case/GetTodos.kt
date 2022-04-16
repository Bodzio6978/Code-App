package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class GetTodos(
    private val repository: TodoRepository
){

    suspend operator fun invoke():Flow<List<Todo>>{
        return repository.getTodoItems()
    }
}