package com.hmyh.moviejc.movieui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.movieui.feature.home.HomeMovieViewModel
import com.hmyh.moviejc.movieui.navagation.MovieNavigation
import com.hmyh.moviejc.network.extension.API_KEY_DATA
import com.hmyh.moviejc.ui.theme.MovieJCTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieJCTheme {
                MovieNavigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieNavigation()
}
