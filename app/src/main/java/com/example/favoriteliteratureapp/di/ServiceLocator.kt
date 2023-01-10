package com.example.favoriteliteratureapp.di

import com.example.favoriteliteratureapp.data.remote.service.FavoriteLiteratureService
import com.example.favoriteliteratureapp.data.remote.service.WebService
import com.example.favoriteliteratureapp.presentation.author.list.AuthorsListViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

const val api_url = "http://10.0.2.2:12000"

object ServiceLocator {
    private var apiFavLit: WebService? = null
    val authorsListViewModel by lazy { AuthorsListViewModel(FavoriteLiteratureService()) }

    fun getApiFavLit(): WebService {
        if (apiFavLit == null) {
            apiFavLit = try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(api_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofit.create(WebService::class.java)
            } catch (e: SocketTimeoutException) {
                print(e.stackTrace)
                null
            }
        }
        return apiFavLit!!
    }
}