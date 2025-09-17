package com.example.contacts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contacts.ui.theme.ContactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactsScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ContactsList(
            listOf(
                Contact(name = "kkk", surname = "lll", phone = "123"),
                Contact(name = "mmm", surname = "oooo", phone = "123456")
            )
        )
        AddNewContact { }
    }
}

@Composable
fun AddNewContact(modifier: Modifier = Modifier, onAddContactClick: () -> Unit) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()

    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.add_contact__title),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        TextInput(stringResource(R.string.add_contact__text_field_label__name))
        TextInput(stringResource(R.string.add_contact__text_field_label__surname))
        TextInput(stringResource(R.string.add_contact__text_field_label__phone))

        StyledButton(
            onClick = onAddContactClick,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.add_contact__button_title)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun StyledButton(onClick: () -> Unit, modifier: Modifier = Modifier, text: String) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(text)
    }
}

@Composable
fun TextInput(label: String) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    )
}

@Composable
fun ContactsList(contacts: List<Contact>) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(8.dp)
    ) {
        for (contact in contacts) {
            Contact(contact)
        }
    }
}

@Composable
fun Contact(contact: Contact) {
    Row(Modifier
        .fillMaxWidth()
        .padding(8.dp),
    ) {
        Surface(
            shape = CircleShape,
            color = Color.Blue,
            contentColor = Color.White,
            modifier = Modifier
                .size(48.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text((contact.name.take(1) + contact.surname.take(1)).uppercase())
            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(contact.name + " " + contact.surname)
            Text(contact.phone)
        }

        Spacer(Modifier.weight(1F))

        StyledButton(
            onClick = {  },
            text = stringResource(R.string.delete_contact_button_title)
        )
    }
}

data class Contact (
    val name: String,
    val surname: String,
    val phone: String
)

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddNewContactPreview() {
    ContactsTheme {
        AddNewContact(
            onAddContactClick = { }
        )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactsListPreview() {
    ContactsTheme {
        ContactsList(
            listOf(
                Contact(name = "kkk", surname = "lll", phone = "123"),
                Contact(name = "mmm", surname = "oooo", phone = "123456")
            )
        )
    }
}