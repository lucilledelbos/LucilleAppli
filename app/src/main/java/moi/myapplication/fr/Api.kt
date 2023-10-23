package moi.myapplication.fr
import retrofit2.http.GET;
import retrofit2.http.Query;
interface Api {

        @GET("trending/movie/week")
        suspend fun lastmovies(@Query("api_key")api_key: String): TmdbMovieResult

        @GET("trending/tv/week")
        suspend fun lastseries(@Query("api_key")api_key: String): TmdbSerieResult

        @GET("trending/person/week")
        suspend fun lastacteurs(@Query("api_key")api_key: String): TmdbActeurResult
}