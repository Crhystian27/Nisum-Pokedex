package co.nisum.basicpokedex.data.remote

import co.nisum.basicpokedex.BuildConfig
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.data.remote.responses.PokemonResponse
import com.google.gson.JsonObject
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApiInterface {

    companion object {

        const val REST = "/api/v2/"

    }

    @GET(BuildConfig.BASE_URL +"${REST}pokemon?")
    suspend fun getRemotePokemonList(@Query("limit") limit: String,
                           @Query("offset") offset: String): Response<PokemonListResponse>

    @GET(BuildConfig.BASE_URL +"${REST}pokemon/{pokemon}")
    fun getPokemon(@Path("pokemon") pokemon: String): Response<PokemonResponse>

    @GET(BuildConfig.BASE_URL +"${REST}pokemon/{pokemon}/encounters")
    fun getPokemonPlaceToFind(@Path("pokemon") pokemon: String): Observable<JsonObject>



}