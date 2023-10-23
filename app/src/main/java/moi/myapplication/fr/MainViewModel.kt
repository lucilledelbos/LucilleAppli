package moi.myapplication.fr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build();

val api = retrofit.create(Api::class.java)
// à partir de là, on peut appeler api.lastMovies(...)

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbSerie>>(listOf())
    val acteurs = MutableStateFlow<List<TmdbActeur>>(listOf())

    val api_key = "bbd091dca96d418202d42dd57a7f58c2"

    init{
        getMovies()
        getSeries()
        getActeurs()
    }
    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key).results
        }
    }
    fun getSeries() {
        viewModelScope.launch {
            series.value = api.lastseries(api_key).results
        }
    }
    fun getActeurs() {
        viewModelScope.launch {
            acteurs.value = api.lastacteurs(api_key).results
        }
    }
}