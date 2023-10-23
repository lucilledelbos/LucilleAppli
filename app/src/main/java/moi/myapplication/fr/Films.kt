package moi.myapplication.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Films(viewModel : MainViewModel, navController: NavController){
    val movies by viewModel.movies.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(2),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(movies.size) { index ->
                    MyMovieCard(movies[index], navController )
                }
        }
    }
    @Composable
    fun MyMovieCard(tmdbMovie: TmdbMovie, navController: NavController){
        val date = if (!tmdbMovie.release_date.isNullOrBlank()) {
            tmdbMovie.release_date
        } else {
            "date inconnue"
        }
        MyCard(
            route = "filmDetail/" + tmdbMovie.id,
            imgPath = tmdbMovie.poster_path,
            firstName = tmdbMovie.title,
            date = date,
            navController = navController
        )
    }


