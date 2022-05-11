package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.use_case.TodoUseCases
import com.gmail.bodziowaty6978.todoapp.util.fromJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val useCases: TodoUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoState = mutableStateOf<TodoTextFieldState>(
        TodoTextFieldState(
            hint = "Enter todo title...",
            text = ""
        )
    )
    val todoState: State<TodoTextFieldState> = _todoState

    private val _todoUiEvent = MutableSharedFlow<AddEditTodoUiEvent>()
    val todoUiEvent: SharedFlow<AddEditTodoUiEvent> = _todoUiEvent

    init {
        val todoJson = savedStateHandle.get<String?>("todo")

        if (todoJson!=null){
            val todo = todoJson.fromJson(Todo::class.java)
            _todoState.value = todoState.value.copy(
                text = todo.title,
                todoId = todo.id
            )
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.EnteredTitle -> {
                _todoState.value = todoState.value.copy(
                    text = event.value
                )
            }
            is AddEditTodoEvent.ChangeTitleFocus -> {
                _todoState.value = todoState.value.copy(
                    isHintVisible = !event.focusState.isFocused && todoState.value.text.isBlank()
                )
            }
            is AddEditTodoEvent.SaveTodo -> {
                viewModelScope.launch(Dispatchers.IO) {
                    var response:Response<Todo>? = null

                    if (todoState.value.todoId!=-1){
                        response = useCases.updateTodo(
                            Todo(
                                title = todoState.value.text,
                                userId = 1, //Current user id
                                id = todoState.value.todoId
                            )
                        )
                    }else{
                        response = useCases.insertTodo(
                            Todo(
                                title = todoState.value.text,
                                userId = 1, //Current user id
                                id = 1
                            )
                        )
                    }

                    if (response.isSuccessful){
                        when(response.code()){
                            200 -> _todoUiEvent.emit(AddEditTodoUiEvent.SaveTodo("Successfully edited todo"))
                            201 -> _todoUiEvent.emit(AddEditTodoUiEvent.SaveTodo("Successfully added todo"))
                            else -> _todoUiEvent.emit(AddEditTodoUiEvent.SaveTodo("Unknown error happened"))
                        }

                    }else{
                        _todoUiEvent.emit(AddEditTodoUiEvent.ShowSnackbar(response.message()))
                    }

                }
            }
            is AddEditTodoEvent.EditingTodo -> {
                _todoState.value = todoState.value.copy(
                    text = event.todo.title,
                    todoId = event.todo.id
                )
            }


        }
    }

}