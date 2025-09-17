package com.aziz.jokebook.data.repository

import com.aziz.jokebook.core.network.RetrofitProvider
import com.aziz.jokebook.data.remote.JokeApi
import com.aziz.jokebook.domain.model.Joke

class DefaultJokeRepository(
    private val api: JokeApi = RetrofitProvider.retrofit.create(JokeApi::class.java)
) : JokeRepository {

    private val cache = mutableMapOf<String, List<Joke>>()

    override suspend fun jokesFor(category: String): Result<List<Joke>> = runCatching {
        cache[category] ?: api.getJokes(category).also { cache[category] = it }
    }
}
