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
    val searchmovies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val filmDetail = MutableStateFlow<TmdbDetailFilm?>(null)
    val serieDetail = MutableStateFlow<TmdbDetailSerie?>(null)
    val acteurDetail = MutableStateFlow<TmdbDetailActeur?>(null)

    val api_key = "bbd091dca96d418202d42dd57a7f58c2"

    init {
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

    fun getSearchMovie(query: String) {
        viewModelScope.launch {
            val res = api.searchmovie(query, api_key, "fr")
            searchmovies.value = res.results
        }

    }
    fun getDetailsFilm(id : Int) {
        viewModelScope.launch {
            if (id != 0) {
                filmDetail.value = api.detailmovie(id, api_key, "fr")
            }
        }
    }
    fun getDetailsSerie(id : Int) {
        viewModelScope.launch {
            if (id != 0) {
                serieDetail.value = api.detailserie(id, api_key, "fr")
            }
        }
    }
    fun getDetailsActeur(id : Int) {
        viewModelScope.launch {
            if (id != 0) {
                acteurDetail.value = api.detailacteur(id, api_key, "fr")
            }
        }
    }
}