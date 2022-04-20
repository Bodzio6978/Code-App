package com.gmail.bodziowaty6978.todoapp.feature_todo.presentation.add_edit_todo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.todoapp.ui.theme.LightRed

@Composable
fun ToolbarSection(
    isTodoEdited:Boolean = false,
    onBackIconPressed:() -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
    ) {

        IconButton(
            onClick = {
                onBackIconPressed()
            },
            modifier = Modifier
                .padding(end = 5.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "back arrow icon",
                tint = LightRed
            )

        }

        Text(
            text = if (isTodoEdited) "Edit Your Todo" else "Add New Todo",
            style = MaterialTheme.typography.h2
        )

    }

}