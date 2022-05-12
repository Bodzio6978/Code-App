package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.shared_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackbar(
    data: SnackbarData
) {

    Snackbar(
        backgroundColor = Color.White,
        content = {
            Text(text = data.message,color = Color.Black)
        },
        modifier = Modifier.padding(12.dp)
    )

}