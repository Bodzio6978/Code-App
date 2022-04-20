package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import retrofit2.Response

interface TodoRepository {

    suspend fun getTodoItems():List<Todo>

    suspend fun insertTodoItem(todo: Todo):Response<Todo>

    suspend fun getTodoItemById(id:Int):Todo?

    suspend fun deleteTodoItem(todoItemId: Int): Response<Todo>

    suspend fun updateTodoItem(todo: Todo):Response<Todo>
}