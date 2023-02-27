package co.nisum.basicpokedex.data.remote

import co.nisum.basicpokedex.BuildConfig
import co.nisum.basicpokedex.data.remote.responses.*
import retrofit2.http.GET
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
    suspend fun getPokemon(@Path("pokemon") pokemon: String): Response<PokemonResponse>

    @GET(BuildConfig.BASE_URL + "${REST}pokemon-species/{pokemon}")
    suspend fun getSpeciesPokemon(@Path("pokemon") pokemon: String): Response<SpeciesResponse>

    @GET(BuildConfig.BASE_URL + "${REST}evolution-chain/{number}")
    suspend fun getEvolutionPokemon(@Path("number") number: String): Response<EvolutionResponse>

    @GET(BuildConfig.BASE_URL +"${REST}pokemon/{pokemon}/encounters")
    suspend fun getLocationPokemon(@Path("pokemon") pokemon: String): Response<List<LocationResponse>>



}