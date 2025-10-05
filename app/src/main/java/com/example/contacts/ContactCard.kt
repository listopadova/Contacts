package com.example.contacts

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val borderWidth = 1.dp
private val cardPadding = 8.dp
private val contactIconSize = 48.dp
@Composable
fun ContactCard(
    modifier: Modifier = Modifier,
    uiState: ContactsListItemState
) {
    OutlinedCard (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(borderWidth, MaterialTheme.colorScheme.primary),
        modifier = modifier
            .padding(cardPadding)
            .fillMaxWidth()
    ) {
            Surface(
                shape = CircleShape,
                color = Color.Blue,
                contentColor = Color.White,
                modifier = Modifier
                    .size(contactIconSize)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(uiState.initials)
                }
            }
            Text(
                uiState.fullName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactCardPreview() {
    ContactCard(
        uiState = ContactsListItemState(
            id = 1,
            name = "Name",
            surname = "Surname",
            phone = "213123123"
        )
    )
}