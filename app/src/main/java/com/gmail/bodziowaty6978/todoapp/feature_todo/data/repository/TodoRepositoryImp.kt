package com.gmail.bodziowaty6978.todoapp.feature_todo.data.repository

import com.gmail.bodziowaty6978.todoapp.feature_todo.data.data_source.TodoApi
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TodoRepositoryImp(
    private val todoApi:TodoApi
): TodoRepository {

    override suspend fun getTodoItems(): Flow<List<Todo>> {
        return flowOf(todoApi.getTodoItems())
    }

    override suspend fun getTodoItemById(id: Int): Todo? {
        val response = todoApi.getTodoItemById(id)
        return response.body()
    }

    override suspend fun insertTodoItem(todo: Todo) {
        todoApi.insertTodoItem(todo)
    }

    override suspend fun deleteTodoItem(todoItemId: Int) {
        todoApi.deleteTodoItem(todoItemId)
    }

    override suspend fun updateTodoItem(todo: Todo) {
        TODO("Not yet implemented")
    }


}