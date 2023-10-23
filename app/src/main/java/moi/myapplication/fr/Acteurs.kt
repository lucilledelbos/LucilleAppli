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
fun Acteurs (viewModel : MainViewModel, navController: NavController) {
    val acteurs by viewModel.acteurs.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(acteurs.size) { index ->
            MyActeursCard(acteurs[index], navController )
        }
    }
}
@Composable
fun MyActeursCard(tmdbActeur: TmdbActeur, navController: NavController){
    MyCard(
        route = "acteursDetail/" + tmdbActeur.id,
        imgPath = tmdbActeur.profile_path,
        firstName = tmdbActeur.name,
        date = "",
        navController = navController
    )

}