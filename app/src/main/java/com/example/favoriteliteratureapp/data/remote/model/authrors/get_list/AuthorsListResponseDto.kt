package com.example.favoriteliteratureapp.data.remote.model.authrors.get_list

import com.example.favoriteliteratureapp.domain.model.Author
import java.util.*

class AuthorsListResponseDto(
    val id: UUID,
    val userName: String,
    val email: String,
    val firstName: String?,
    val lastName: String?,
    val patronymic: String?,
)

fun AuthorsListResponseDto.toDomain() = Author(
    id = id,
    userName = userName,
    email = email,
    firstName = firstName ?: "Anonymous",
    lastName = lastName ?: "Anonymous",
    patronymic = patronymic ?: "Anonymous"
)