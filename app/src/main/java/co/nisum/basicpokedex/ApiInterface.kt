package co.nisum.basicpokedex

import com.google.gson.JsonObject
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    companion object {

        const val REST = "/api/v2/"

    }

    @GET(BuildConfig.BASE_URL+"${REST}pokemon?")
    fun getPokemonsNamesList(@Query("limit") limit: String,
                           @Query("offset") offset: String): Observable<JsonObject>

    @GET(BuildConfig.BASE_URL+"${REST}pokemon/{pokemon}")
    fun getPokemon(@Path("pokemon") pokemon: String): Observable<JsonObject>

    @GET(BuildConfig.BASE_URL+"${REST}pokemon/{pokemon}/encounters")
    fun getPokemonPlaceToFind(@Path("pokemon") pokemon: String): Observable<JsonObject>



}