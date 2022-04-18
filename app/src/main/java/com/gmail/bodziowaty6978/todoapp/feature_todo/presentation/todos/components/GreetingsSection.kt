package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.todos.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.todoapp.ui.theme.TextWhite

@Composable
fun GreetingsSection(
    userName: String = "John",
    isDeleteVisible: Boolean = false
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                text = "Welcome $userName",
                style = MaterialTheme.typography.h2,
                color = TextWhite
            )

            Text(
                text = "We hope you are having a great day",
                style = MaterialTheme.typography.body2,
                color = TextWhite
            )
        }

        if (isDeleteVisible) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {

                    }
            )
        }
    }

}