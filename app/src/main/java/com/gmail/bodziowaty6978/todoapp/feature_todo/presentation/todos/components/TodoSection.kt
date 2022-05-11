package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.TodoEvent


@Composable
fun TodoSection(
    todoItems: List<Todo>,
    onEvent: (TodoEvent) -> Unit
) {
    LazyColumn {
        items(todoItems.size) { it ->
            TodoItem(
                todo = todoItems[it],
                onEvent = { todoEvent ->
                    onEvent(todoEvent)
                },
                modifier = if (it == todoItems.size - 1) Modifier.padding(bottom = 100.dp) else Modifier
            )
        }
    }

}