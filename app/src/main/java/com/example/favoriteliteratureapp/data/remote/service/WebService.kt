package com.example.favoriteliteratureapp.data.remote.service

import com.example.favoriteliteratureapp.data.remote.model.authrors.get_list.AuthorsListResponseDto
import com.example.favoriteliteratureapp.data.remote.model.books.get_list.BooksListResponseDto
import retrofit2.http.GET

const val path = "api"
const val books = "$path/books"
const val authors = "$path/authors"

interface WebService {
    @GET(books)
    suspend fun getAllBooks(): List<BooksListResponseDto>

    @GET(authors)
    suspend fun getAllAuthors(): List<AuthorsListResponseDto>
}