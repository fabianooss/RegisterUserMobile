package com.example.registeruser.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.registeruser.ui.theme.RegisterUserTheme

@Composable
fun RegisterUserMainScreen() {
    val registerUserViewModel : RegisterUserViewModel = viewModel()

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            RegisterUserFields(registerUserViewModel)
        }
    }
}

@Composable
fun RegisterUserFields(registerUserViewModel: RegisterUserViewModel) {
    var registerUser = registerUserViewModel.uiState.collectAsState()
    val ctx = LocalContext.current

    OutlinedTextField(
        value = registerUser.value.user ,
        onValueChange = {
                        registerUserViewModel.onUserChange(it)
        },
        singleLine = true,
        label = {
            Text(text = "User")
        },
       // modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = registerUser.value.email ,
        onValueChange = {
                        registerUserViewModel.onEmailChange(it)
        },
        singleLine = true,
        label = {
            Text(text = "Email")
        }
    )
    OutlinedTextField(
        value = registerUser.value.password ,
        onValueChange = {
                        registerUserViewModel.onPasswordChange(it)
        },
        singleLine = true,
        label = {
            Text(text = "Password")
        },
        visualTransformation = PasswordVisualTransformation()
    )
    OutlinedTextField(
        value = registerUser.value.confirmPassword ,
        onValueChange = {
                        registerUserViewModel.onConfirmPassword(it)
        },
        singleLine = true,
        label = {
            Text(text = "Confirm password")
        },
        visualTransformation = PasswordVisualTransformation()
    )

    Button(
        modifier = Modifier.padding(top = 16.dp),
        onClick = {
            Toast.makeText(ctx, "Mensagem",
                Toast.LENGTH_SHORT).show()

        }
    ) {
        Text(text = "Register user")
        
    }



}

@Composable
@Preview(showSystemUi = true, showBackground = true, device = "id:Galaxy Nexus")
fun RegisterUserPreview() {
    RegisterUserTheme {
        RegisterUserMainScreen()
    }
}