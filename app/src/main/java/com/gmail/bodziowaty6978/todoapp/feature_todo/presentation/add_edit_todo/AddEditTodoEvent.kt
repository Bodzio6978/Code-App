package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo

import androidx.compose.ui.focus.FocusState
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo

sealed class AddEditTodoEvent {
    data class EnteredTitle(val value:String):AddEditTodoEvent()
    data class ChangeTitleFocus(val focusState:FocusState):AddEditTodoEvent()
    data class EditingTodo(val todo:Todo):AddEditTodoEvent()
    object SaveTodo:AddEditTodoEvent()
}