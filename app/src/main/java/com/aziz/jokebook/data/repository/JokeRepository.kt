package com.aziz.jokebook.data.repository

import com.aziz.jokebook.domain.model.Joke

interface JokeRepository {
    suspend fun jokesFor(category: String): Result<List<Joke>>
}
