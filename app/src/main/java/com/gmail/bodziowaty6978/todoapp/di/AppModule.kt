package com.gmail.bodziowaty6978.todoapp.di

import com.gmail.bodziowaty6978.todoapp.feature_todo.data.data_source.TodoApi
import com.gmail.bodziowaty6978.todoapp.feature_todo.data.repository.TodoRepositoryImp
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.repository.TodoRepository
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTodoRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    @Singleton
    @Provides
    fun provideTodoApi(retrofitInstance:Retrofit):TodoApi = retrofitInstance.create(TodoApi::class.java)

    @Singleton
    @Provides
    fun provideTodoRepository(todoApi: TodoApi):TodoRepository = TodoRepositoryImp(todoApi)

    @Singleton
    @Provides
    fun provideUseCases(repository:TodoRepository) = TodoUseCases(
        getTodos = GetTodos(repository),
        getTodoById = GetTodoById(repository),
        insertTodo = InsertTodo(repository),
        deleteTodo = DeleteTodo(repository),
        updateTodo = UpdateTodo(repository)
    )

}