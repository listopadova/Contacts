package com.example.contacts.presentation.addNewContact

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contacts.R
import com.example.contacts.components.StyledButton
import com.example.contacts.components.TextInput
import com.example.contacts.ui.theme.ContactsTheme

private val cardPadding = 8.dp
private val spacerHeight = 12.dp
private val buttonPadding = 12.dp
private val buttonHeight = 48.dp

@Composable
fun AddNewContact(
    modifier: Modifier = Modifier,
    viewModel: AddNewContactViewModel = viewModel(factory = AddNewContactViewModel.Companion.Factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .padding(cardPadding)
            .fillMaxWidth()

    ) {
        Column {
            Spacer(modifier = Modifier.height(spacerHeight))

            TextInput(
                label = stringResource(R.string.add_contact__text_field_label__name),
                text = uiState.name,
                supportingText = if (uiState.isEmptyNameError)
                    stringResource(R.string.add_contact__text_field_label__empty_name_error) else null,
                isError = uiState.isEmptyNameError,
                updateValue = viewModel::updateName
            )
            TextInput(
                label = stringResource(R.string.add_contact__text_field_label__surname),
                text = uiState.surname,
                supportingText = if (uiState.isEmptySurnameError)
                    stringResource(R.string.add_contact__text_field_label__empty_surname_error) else null,
                isError = uiState.isEmptySurnameError,
                updateValue = viewModel::updateSurname
            )
            TextInput(
                label = stringResource(R.string.add_contact__text_field_label__phone),
                text = uiState.phone,
                supportingText = if (uiState.isEmptyPhoneError)
                    stringResource(R.string.add_contact__text_field_label__empty_phone_error) else null,
                isError = uiState.isEmptyPhoneError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                updateValue = viewModel::updatePhone
            )
            TextInput(
                label = stringResource(R.string.add_contact__text_field_label__email),
                text = uiState.email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                updateValue = viewModel::updateEmail
            )
        }

        Spacer(Modifier.weight(1F))
        StyledButton(
            onClick = {
                focusManager.clearFocus()
                viewModel.addContact()
                // TODO: go back to the list
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(buttonPadding)
                .height(buttonHeight),
            cornerRadius = buttonHeight / 2,
            text = stringResource(R.string.add_contact__button_title)
        )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AddNewContactPreview() {
    ContactsTheme {
        AddNewContact()
    }
}