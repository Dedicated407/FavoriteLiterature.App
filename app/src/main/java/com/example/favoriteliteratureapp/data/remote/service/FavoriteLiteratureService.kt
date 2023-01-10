package com.example.favoriteliteratureapp.data.remote.service

import com.example.favoriteliteratureapp.data.remote.model.authrors.get_list.AuthorsListResponseDto
import com.example.favoriteliteratureapp.data.remote.model.books.get_list.BooksListResponseDto
import com.example.favoriteliteratureapp.di.ServiceLocator
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class FavoriteLiteratureService {
    private var mApiFavLit: WebService = ServiceLocator.getApiFavLit()

    suspend fun getAllBooks(): List<BooksListResponseDto> = catchAll {
        mApiFavLit.getAllBooks()
    }

    suspend fun getAllAuthors(): List<AuthorsListResponseDto> = catchAll {
        mApiFavLit.getAllAuthors()
    }

    private suspend inline fun<reified T> catchAll(
        crossinline block: suspend () -> T,
    ): T = try {
        retry(block)
    } catch (e: EOFException) {
        null as T
    } catch (e: UnknownHostException) {
        null as T
    } catch (e: ConnectException) {
        null as T
    } catch (e: retrofit2.HttpException) {
        e.printStackTrace()
        throw e
    } catch (e: Throwable) {
        e.printStackTrace()
        throw e
    }

    private suspend inline fun<reified T> retry(
        crossinline block: suspend () -> T,
    ): T {
        repeat(MAX_RETRY_COUNT) {
            try {
                return block()
            } catch (e: SocketTimeoutException) {
                // Ignore (delay included in timeout)
            }
        }
        return null as T
    }

    companion object {
        const val MAX_RETRY_COUNT = 4
    }
}