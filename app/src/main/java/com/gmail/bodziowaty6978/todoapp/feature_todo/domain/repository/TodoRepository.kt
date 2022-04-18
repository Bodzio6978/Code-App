package com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo

interface TodoRepository {

    suspend fun getTodoItems():List<Todo>

    suspend fun insertTodoItem(todo: Todo)

    suspend fun getTodoItemById(id:Int):Todo?

    suspend fun deleteTodoItem(todoItemId: Int)

    suspend fun updateTodoItem(todo: Todo)
}