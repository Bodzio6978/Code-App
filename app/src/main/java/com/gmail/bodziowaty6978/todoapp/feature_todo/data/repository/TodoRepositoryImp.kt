package com.gmail.bodziowaty6978.todoapp.feature_todo.data.repository

import com.gmail.bodziowaty6978.todoapp.feature_todo.data.data_source.TodoApi
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository
import retrofit2.Response


class TodoRepositoryImp(
    private val todoApi:TodoApi
): TodoRepository {

    override suspend fun getTodoItems(): List<Todo> {
        return todoApi.getTodoItems()
    }

    override suspend fun getTodoItemById(id: Int): Todo? {
        val response = todoApi.getTodoItemById(id)
        return response.body()
    }

    override suspend fun insertTodoItem(todo: Todo): Response<Todo> {
        return todoApi.insertTodoItem(todo)
    }

    override suspend fun deleteTodoItem(todoItemId: Int):Response<Todo> {
        return todoApi.deleteTodoItem(todoItemId)
    }

    override suspend fun updateTodoItem(todo: Todo):Response<Todo> {
       return todoApi.updateTodoItem(todo.id,todo)
    }
}