package com.hmyh.moviejc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.hmyh.moviejc.appbase.core.renderState
import com.hmyh.moviejc.movieui.feature.MovieViewModel
import com.hmyh.moviejc.network.extension.API_KEY_DATA
import com.hmyh.moviejc.ui.theme.MovieJCTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            try {
                movieViewModel.getNowPlayingMoviesList(API_KEY_DATA)
            } catch (e: Exception) {
                Timber.e("Error in ViewModel operations: ${e.message}")
            }
        }

        renderState()

        setContent {
            MovieJCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun renderState() {

        lifecycleScope.launch {
            try {
                movieViewModel.movieListFlow.collect {
                    it.renderState(
                        success = {
                            Timber.i("first title ${it.firstOrNull()?.title.toString()}")
                        }
                    )
                }
            } catch (e: Exception) {
                Timber.e("Error in renderState: ${e.message}")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieJCTheme {
        Greeting("Android")
    }
}