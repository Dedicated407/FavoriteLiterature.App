package com.example.favoriteliteratureapp.presentation.author.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoriteliteratureapp.data.remote.model.authrors.get_list.toDomain
import com.example.favoriteliteratureapp.data.remote.service.FavoriteLiteratureService
import com.example.favoriteliteratureapp.domain.model.Author
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthorsListViewModel(
    val service: FavoriteLiteratureService
) : ViewModel() {
    private val _authors = MutableStateFlow(emptyList<Author>())
    val authors = _authors.asStateFlow()

    suspend fun getAllAuthors() =
        viewModelScope.launch {
            val authors = service.getAllAuthors().map {
                it.toDomain()
            }

            _authors.value = authors
        }
}