package moi.myapplication.fr


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import moi.myapplication.fr.ui.theme.LucilleApplicationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel: MainViewModel by viewModels()

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val backStackEntry by navController.currentBackStackEntryAsState()
            var recherche = remember { mutableStateOf(false) }
            LucilleApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            if (currentDestination?.route != "Profil") {
                                NavigationBar() {
                                    NavigationBarItem(
                                        icon = {
                                            Image(
                                                painterResource(id = R.drawable.clap),
                                                contentDescription = "Films",
                                                modifier = Modifier
                                                    .size(50.dp)

                                            )
                                        },
                                        label = {},
                                        selected = false,
                                        onClick = { navController.navigate("Films") }
                                    )
                                    NavigationBarItem(
                                        icon = {
                                            Image(
                                                painterResource(id = R.drawable.series),
                                                contentDescription = "Series",
                                                modifier = Modifier
                                                    .size(65.dp)
                                            )
                                        },
                                        label = { },
                                        selected = false,
                                        onClick = { navController.navigate("Series") }
                                    )
                                    NavigationBarItem(
                                        icon = {
                                            Image(
                                                painterResource(id = R.drawable.acteur),
                                                contentDescription = "Acteurs",
                                                modifier = Modifier
                                                    .size(50.dp)
                                            )
                                        },
                                        label = {},
                                        selected = false,
                                        onClick = { navController.navigate("Acteurs") }
                                    )
                                }
                            }
                        },
                        topBar = {

                            if (currentDestination?.route != "Profil") {
                                TopAppBar(
                                    title = {
                                        if (!recherche.value) {
                                            Text(text = "LuluAppli")
                                        } else {
                                            Search(navController)
                                        }
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = { recherche.value =! recherche.value }) {
                                            Image(
                                                painterResource(id = R.drawable.loupe),
                                                contentDescription = "Acteurs",
                                                modifier = Modifier
                                                    .size(30.dp)
                                            )
                                        }
                                    },
                                    actions = {
                                        // RowScope here, so these icons will be placed horizontally
                                        IconButton(onClick = { }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                contentDescription = "Localized description"
                                            )
                                        }

                                    }
                                )
                            }
                        }
                    )
                    { innerPadding ->
                        NavHost(
                            navController = navController, startDestination = "Profil",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("Profil") {
                                ScreenP(
                                    windowClass = windowSizeClass,
                                    navController = navController
                                )
                            }
                            composable("Films") {
                                val movies by viewmodel.movies.collectAsState()
                                Films(
                                    viewModel = viewmodel,
                                    navController = navController,
                                    movies = movies
                                )
                            }
                            composable("Series") {
                                Series(
                                    viewModel = viewmodel,
                                    navController = navController
                                )
                            }
                            composable("Acteurs") {
                                Acteurs(
                                    viewModel = viewmodel,
                                    navController = navController
                                )
                            }
                            composable(
                                "filmsSearch/{searchTerm}",
                                arguments = listOf(navArgument("searchTerm") {
                                    type = NavType.StringType
                                })
                            ) {
                                backStackEntry?.arguments?.getString("searchTerm")
                                    ?.let { it1 ->
                                        viewmodel.getSearchMovie(it1)
                                        val movies2 by viewmodel.searchmovies.collectAsState()
                                        Films(viewmodel, navController, movies2)
                                    }

                            }
                        }

                    }

                }
            }


            @Composable
            fun Clap() {
                Image(
                    painterResource(id = R.drawable.clap),
                    contentDescription = "Films",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(20.dp)

                )
            }

        }
    }
}


