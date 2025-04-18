package com.example.registeruser.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.registeruser.database.AppDatabase
import com.example.registeruser.entity.User
import com.example.registeruser.ui.theme.RegisterUserTheme
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserListScreen(onInsertOrEdit: (Int?) -> Unit) {
    val ctx = LocalContext.current
    val userDao = AppDatabase.getDatabase(ctx).userDao()
    val listViewModel : RegisterUserListViewModel = viewModel(
        factory = RegisterUserListViewModelFactory(userDao)
    )
    val userState = listViewModel.users.collectAsState(
        emptyList()
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title =  {
                    Text(text = "List of users")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onInsertOrEdit(null)
                }
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                 items(items = userState.value) { user ->
                    ItemUser(user, onEdit = {
                        onInsertOrEdit(it)
                    })
                }
            }

        }

    }

}

@Composable
fun ItemUser(user: User,
             onEdit: (Int) -> Unit ) {
    Card (modifier =
        Modifier.padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(2.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onEdit(user.id)
                    }
                )
            }

        )
        {
            Text(text = user.name, style = MaterialTheme.typography.displayLarge)
            Text(text = user.email)
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun PreviewList() {
    RegisterUserTheme {
        RegisterUserListScreen(onInsertOrEdit = {})
    }
}