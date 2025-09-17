package com.aziz.jokebook.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aziz.jokebook.domain.model.Joke

@Composable
fun PunchlineScreen(joke: Joke, onBack: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ✅ Back button at the top
            TextButton(onClick = onBack) {
                Text("← Back")
            }

            Spacer(Modifier.height(48.dp))

            // Setup (centered, smaller)
            Text(
                text = joke.setup,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))
            Divider()
            Spacer(Modifier.height(16.dp))

            // Punchline (centered, bigger, main focus)
            Text(
                text = joke.punchline,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}
