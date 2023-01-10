package com.example.favoriteliteratureapp.presentation.author.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.favoriteliteratureapp.di.ServiceLocator
import com.example.favoriteliteratureapp.domain.model.Author
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun AuthorsListScreen(
    navigator: DestinationsNavigator,
    navController: NavController
) {
    val viewModel = ServiceLocator.authorsListViewModel
    val authors by viewModel.authors.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllAuthors()
    }

    LazyColumn{
        items(authors) { item ->
            AuthorsCard(item)
        }
    }
}

@Composable
fun AuthorsCard(author: Author) = with(author) {
    val paddingModifier  = Modifier.padding(10.dp)
    Card(elevation = 10.dp, modifier = paddingModifier) {
        Column {
            Text(text = "Пользовательское имя: $userName", modifier = paddingModifier)

            Text(
                text = "ФИО: $lastName $firstName $patronymic",
                modifier = paddingModifier
            )

            Text(text = "Почта: $email", modifier = paddingModifier)
            Text(text = "Рейтинг: $rating", modifier = paddingModifier)
        }
    }
}