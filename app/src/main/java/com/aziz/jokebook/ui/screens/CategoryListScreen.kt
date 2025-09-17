package com.aziz.jokebook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aziz.jokebook.domain.model.Joke
import com.aziz.jokebook.ui.state.JokesUiState

@Composable
fun CategoryListScreen(
    state: JokesUiState,
    onCategoryChange: (String) -> Unit,
    onRefresh: () -> Unit,
    onSelect: (Joke) -> Unit
) {
    val categories = listOf("general", "knock-knock", "programming")
    val labels = mapOf(
        "general" to "General",
        "knock-knock" to "Knock-knock",
        "programming" to "Programming"
    )

    // Auto-fetch on initial show and whenever the category changes
    LaunchedEffect(state.category) { onRefresh() }

    Scaffold(
        // Respect system bars (status/nav) so nothing is clipped
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Scrollable tabs scale better on small screens & high font scales
            val selectedIndex = categories.indexOf(state.category).coerceAtLeast(0)
            ScrollableTabRow(selectedTabIndex = selectedIndex) {
                categories.forEachIndexed { index, key ->
                    Tab(
                        selected = index == selectedIndex,
                        onClick = { if (index != selectedIndex) onCategoryChange(key) },
                        text = {
                            Text(
                                labels[key] ?: key,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            when {
                state.isLoading -> {
                    Column(Modifier.padding(horizontal = 16.dp)) {
                        LinearProgressIndicator(Modifier.fillMaxWidth())
                        Spacer(Modifier.height(8.dp))
                        Text("Loading jokes…")
                    }
                }
                state.error != null -> {
                    Column(Modifier.padding(16.dp)) {
                        Text("Error: ${state.error}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = onRefresh) { Text("Retry") }
                    }
                }
                else -> {
                    // Make the list take remaining height and pad for nav bar/IME
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(
                            start = 8.dp, end = 8.dp, top = 4.dp, bottom = 12.dp
                        )
                    ) {
                        itemsIndexed(state.jokes) { i, joke ->
                            ListItem(
                                headlineContent = { Text(joke.setup) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSelect(joke) }
                            )
                            if (i < state.jokes.lastIndex) Divider()
                        }
                    }
                }
            }
        }
    }
}
