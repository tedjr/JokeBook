package com.aziz.jokebook.ui.state

import com.aziz.jokebook.domain.model.Joke

data class JokesUiState(
    val category: String = "general",
    val isLoading: Boolean = false,
    val error: String? = null,
    val jokes: List<Joke> = emptyList(),
    val selectedJoke: Joke? = null
)
