package com.example.favoriteliteratureapp.domain.model

import java.util.*

data class Author(
    val id: UUID,
    val userName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val description: String? = null,
    val birthday: Date? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val rating: Byte = 0,
)