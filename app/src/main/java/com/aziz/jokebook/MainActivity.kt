package com.aziz.jokebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aziz.jokebook.ui.state.JokeViewModel
import com.aziz.jokebook.ui.navigation.NavRoutes
import com.aziz.jokebook.ui.screens.CategoryListScreen
import com.aziz.jokebook.ui.screens.CoverScreen
import com.aziz.jokebook.ui.screens.PunchlineScreen
import com.aziz.jokebook.ui.state.JokesUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: JokeViewModel = viewModel()
            val state: JokesUiState by vm.state.collectAsState(initial = JokesUiState())
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = NavRoutes.Cover) {
                composable(NavRoutes.Cover) {
                    CoverScreen(onEnter = { nav.navigate(NavRoutes.Categories) })
                }
                composable(NavRoutes.Categories) {
                    CategoryListScreen(
                        state = state,
                        onCategoryChange = vm::setCategory,
                        onRefresh = vm::fetchJokes,
                        onSelect = { joke ->
                            vm.selectJoke(joke)
                            nav.navigate(NavRoutes.Punchline)
                        }
                    )
                }
                composable(NavRoutes.Punchline) {
                    val joke = state.selectedJoke
                    if (joke != null) {
                        PunchlineScreen(joke = joke) { nav.popBackStack() }
                    } else {
                        nav.popBackStack()
                    }
                }
            }
        }
    }
}
