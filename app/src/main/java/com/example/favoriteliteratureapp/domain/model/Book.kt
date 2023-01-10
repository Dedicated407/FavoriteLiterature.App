package com.example.favoriteliteratureapp.domain.model

import java.util.*

data class Book(
    val id: UUID,
    val name: String,
    val author: Author,
    val description: String?,
    val rating: Byte
)