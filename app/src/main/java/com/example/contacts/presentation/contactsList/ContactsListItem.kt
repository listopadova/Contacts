package com.example.contacts.presentation.contactsList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.contacts.R

private val contactIconSize = 48.dp
private val padding = 8.dp

@Composable
fun ContactsListItem(
    modifier: Modifier = Modifier,
    uiState: ContactsListItemState,
    onClick: () -> Unit
) {
    Row(modifier
        .fillMaxWidth()
        .padding(padding),
    ) {
        Surface(
            shape = CircleShape,
            color = Color.Blue,
            contentColor = Color.White,
            modifier = Modifier
                .size(contactIconSize)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(uiState.initials)
            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = padding)
        ) {
            Text(uiState.fullName)
            Text(uiState.phone)
        }

        Spacer(Modifier.weight(1F))

        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(R.drawable.favourite_24),
                tint = if (uiState.isFavourite) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.primary,
                contentDescription = ""
            )
        }
    }
}
