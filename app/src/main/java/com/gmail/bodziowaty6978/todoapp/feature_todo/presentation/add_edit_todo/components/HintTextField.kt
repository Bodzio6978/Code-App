package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.todoapp.ui.theme.Grey
import com.gmail.bodziowaty6978.todoapp.ui.theme.TextWhite

@Composable
fun HintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.h2.copy(
                color = TextWhite
            ),
            modifier = Modifier
                .padding(15.dp)
                .padding(bottom = 80.dp)
                .fillMaxSize()
                .onFocusChanged {
                    onFocusChange(it)
                }
                .clip(RoundedCornerShape(10.dp))
                .background(Grey)
                .padding(10.dp)



        )
        if (isHintVisible) {
            Text(
                modifier = Modifier
                    .padding(25.dp),
                text = hint,
                style = MaterialTheme.typography.h2,
                color = Color.Gray
            )
        }
    }

}