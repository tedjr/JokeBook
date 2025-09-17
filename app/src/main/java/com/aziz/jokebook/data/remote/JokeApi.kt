package com.aziz.jokebook.data.remote

import com.aziz.jokebook.domain.model.Joke
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeApi {
    @GET("jokes/{category}/ten")
    suspend fun getJokes(@Path("category") category: String): List<Joke>
}
