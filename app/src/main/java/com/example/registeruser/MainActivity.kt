package com.example.registeruser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.registeruser.screens.RegisterUserListScreen
import com.example.registeruser.screens.RegisterUserMainScreen
import com.example.registeruser.ui.theme.RegisterUserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterUserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MyRegisterApplication()
                }
            }
        }
    }
}


@Composable
fun MyRegisterApplication() {
    val navController = rememberNavController()
    Column {
        NavHost(navController = navController, startDestination = "List") {

            composable(route = "List") {
                RegisterUserListScreen(
                    onInsertOrEdit = {
                        if (it == null)
                            navController.navigate("form")
                        else
                            navController.navigate("form/${it}")
                    }
                )

            }
            composable(
                route = "form") {
                RegisterUserMainScreen(null)
            }

            composable(
                route = "form/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType})) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                RegisterUserMainScreen(id)
            }
        }
    }

}
