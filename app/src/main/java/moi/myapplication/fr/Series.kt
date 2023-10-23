package moi.myapplication.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Series (viewModel : MainViewModel, navController: NavController, series : List<TmdbSerie>) {


    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(series.size) { index ->
            MySeriesCard(series[index], navController )
        }
    }
}
@Composable
fun MySeriesCard(tmdbSerie: TmdbSerie, navController: NavController){
    val date = if (!tmdbSerie.first_air_date.isNullOrBlank()) {
        tmdbSerie.first_air_date
    } else {
        "date inconnue"
    }
    MyCard(
        route = "seriesDetail/" + tmdbSerie.id,
        imgPath = tmdbSerie.poster_path,
        firstName = tmdbSerie.name,
        date = date,
        navController = navController
    )
}