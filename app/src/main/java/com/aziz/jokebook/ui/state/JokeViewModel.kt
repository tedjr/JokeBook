package com.aziz.jokebook.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aziz.jokebook.data.repository.DefaultJokeRepository
import com.aziz.jokebook.data.repository.JokeRepository
import com.aziz.jokebook.domain.model.Joke
import com.aziz.jokebook.ui.state.JokesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JokeViewModel(
    private val repo: JokeRepository = DefaultJokeRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(JokesUiState())
    val state: StateFlow<JokesUiState> = _state

    fun setCategory(category: String) {
        _state.value = _state.value.copy(category = category)
    }

    fun fetchJokes() {
        val category = _state.value.category
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = repo.jokesFor(category)
            _state.value = result.fold(
                onSuccess = { _state.value.copy(isLoading = false, jokes = it) },
                onFailure = { _state.value.copy(isLoading = false, error = it.message ?: "Unknown error") }
            )
        }
    }

    fun selectJoke(joke: Joke) {
        _state.value = _state.value.copy(selectedJoke = joke)
    }

    fun clearSelection() {
        _state.value = _state.value.copy(selectedJoke = null)
    }
}
