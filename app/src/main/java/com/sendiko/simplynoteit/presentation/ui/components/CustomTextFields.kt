package com.sendiko.simplynoteit.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.simplynoteit.presentation.ui.theme.nunitoFont

/**
 *
 *  Sendiko's Custom OutlinedTextField
 *  @param value is for the text value.
 *  @param onNewValue return the new text when user type.
 *  @param label is big text on top of the textfield.
 *  @param hint is helper text inside the textfield.
 *  @param leadingIcon icon before text.
 *  @param onClearValue function that intended to clear the text.
 *  @param isError is to state whether there's error or not.
 *  @param errorMessage is to show error message.
 *  @param supportingText if not error, this supporting text will shows instead.
 *  @param isPasswordTextField to set if the textfield contains password, it'll hide the text.
 *  @param isPasswordVisible set if the text is visible or not.
 *  @param onPasswordVisibilityToggle is to toggle the visibility.
 *  @param keyboardType is to specified type input.
 *
 * */
@Composable
fun OutlinedTextField(
    value: String,
    onNewValue: (String) -> Unit,
    label: String,
    hint: String,
    leadingIcon: ImageVector,
    onClearValue: () -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    supportingText: String? = null,
    isPasswordTextField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityToggle: (Boolean) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            fontFamily = nunitoFont
        )
        OutlinedTextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                onNewValue(it)
            },
            shape = CircleShape,
            placeholder = {
                Text(text = hint)
            },
            leadingIcon = {
                Icon(imageVector = leadingIcon, contentDescription = null)
            },
            trailingIcon = {
                if (value.isNotBlank() && !isPasswordTextField) {
                    IconButton(
                        onClick = onClearValue,
                        content = {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "clear text"
                            )
                        }
                    )
                }
                if (isPasswordTextField){
                    IconButton(
                        onClick = {
                            onPasswordVisibilityToggle(!isPasswordVisible)
                        },
                        content = {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "visible"
                            )
                        }
                    )
                }
            },
            isError = isError,
            supportingText = {
                (if (isError) errorMessage else supportingText)?.let { Text(text = it) }
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if(isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )
    }
}