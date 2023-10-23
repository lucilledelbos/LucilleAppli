package moi.myapplication.fr
import retrofit2.http.GET;
import retrofit2.http.Path
import retrofit2.http.Query;
interface Api {

        @GET("trending/movie/week")
        suspend fun lastmovies(@Query("api_key")api_key: String): TmdbMovieResult

        @GET("trending/tv/week")
        suspend fun lastseries(@Query("api_key")api_key: String): TmdbSerieResult

        @GET("trending/person/week")
        suspend fun lastacteurs(@Query("api_key")api_key: String): TmdbActeurResult

        @GET("search/movie")
        suspend fun searchmovie(
                @Query("query") query: String,
                @Query("api_key") api_key: String,
                @Query("language") language: String
        ): TmdbMovieResult

        @GET ("movie/{id}")
        suspend fun detailmovie(
                @Path("id") id: Int,
                @Query("api_key") api_key: String,
                @Query("language") language: String,
                @Query("append_to_response") append_to_response: String = "credits"
        ): TmdbDetailFilm

        @GET ("tv/{id}")
        suspend fun detailserie(
                @Path("id") id: Int,
                @Query("api_key") api_key: String,
                @Query("language") language: String,
                @Query("append_to_response") append_to_response: String = "credits"
        ): TmdbDetailSerie

        @GET ("person/{id}")
        suspend fun detailacteur(
                @Path("id") id: Int,
                @Query("api_key") api_key: String,
                @Query("language") language: String,
                @Query("append_to_response") append_to_response: String = "credits"
        ): TmdbDetailActeur

        @GET("search/person")
        suspend fun searchacteur(
                @Query("query") query: String,
                @Query("api_key") api_key: String,
                @Query("language") language: String
        ): TmdbActeurResult

        @GET("search/tv")
        suspend fun searchseries(
                @Query("query") query: String,
                @Query("api_key") api_key: String,
                @Query("language") language: String
        ): TmdbSerieResult
}