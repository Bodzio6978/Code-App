package com.gmail.bodziowaty6978.todoapp.feature_todo.data.data_source

import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import retrofit2.Response
import retrofit2.http.*

interface TodoApi {

    @GET("/todos")
    suspend fun getTodoItems(): List<Todo>

    @GET("/todos/{id}")
    suspend fun getTodoItemById(@Path("id") id: Int): Response<Todo>

    @POST("/todos")
    suspend fun insertTodoItem(@Body todo: Todo): Response<Todo>

    @DELETE("todos/1")
    suspend fun deleteTodoItem(@Path("id") todoItemId: Int)

    @PUT("todos/{id}")
    suspend fun updateTodoItem(
        @Path("id") id: Int,
        @Body todo: Todo
    ): Response<Todo>

}