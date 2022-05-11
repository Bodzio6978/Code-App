package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.InvalidTodoException
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository
import retrofit2.Response

class InsertTodo(
    private val repository: TodoRepository
){

    @Throws(InvalidTodoException::class)
    suspend operator fun invoke(todo:Todo): Response<Todo> {
        if (todo.title.isBlank()){
            throw InvalidTodoException("The title can't be empty")
        }
        return repository.insertTodoItem(todo)
    }
}