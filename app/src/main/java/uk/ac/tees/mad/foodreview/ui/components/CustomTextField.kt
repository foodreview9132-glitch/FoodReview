package uk.ac.tees.mad.foodreview.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange:(String)-> Unit,
    icon : ImageVector = Icons.Default.Email,
    onIconClick:((Boolean)-> Unit)? = null,
    isPasswordVisible : Boolean = false,
    isPasswordField : Boolean = false ,
    label : String,
    placeholder : String
){
    OutlinedTextField(
        onValueChange = onValueChange ,
        value = value ,
        label = {
            Text(
                text = label
            )
        },
        placeholder = {
          Text(
              text = placeholder
          )
        },
        trailingIcon = {
            Icon(
                imageVector = icon ,
                contentDescription = null ,
                modifier = Modifier.clickable{
                   onIconClick?.invoke(
                       !isPasswordVisible
                   )
                }
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,

            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),

            cursorColor = MaterialTheme.colorScheme.primary
        )
        ,

        visualTransformation =
            if (isPasswordField && !isPasswordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,

        modifier = modifier.fillMaxWidth() ,
        maxLines = 1 ,
        singleLine = true ,
        shape = MaterialTheme.shapes.medium
    )
}

/**
 * this is the custom textfield will be used by
 * email , password and confirm password
 */