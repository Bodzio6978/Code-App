package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.todoapp.feature_todo.domain.model.Todo
import com.gmail.bodziowaty6978.todoapp.ui.theme.ButtonBlue
import com.gmail.bodziowaty6978.todoapp.ui.theme.Grey
import com.gmail.bodziowaty6978.todoapp.ui.theme.TextWhite

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Grey)
            .padding(start = 15.dp, top = 5.dp, bottom = 10.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.h2,
                color = TextWhite,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )

            Text(
                text = "User ID: ${todo.userId}",
                style = MaterialTheme.typography.body1,
                color = TextWhite,
                maxLines = 1
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .clickable {
                    onEvent()
                }
                .padding(10.dp)

        ){
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check icon",
                tint = Color.White,
                modifier = Modifier
                    .size(16.dp)
            )
        }
    }


}


