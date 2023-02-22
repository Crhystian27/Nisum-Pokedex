package co.nisum.basicpokedex.data.repository.dataSource

import co.nisum.basicpokedex.data.remote.responses.AbilityResponse
import co.nisum.basicpokedex.data.remote.responses.EncountersListResponse
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.data.remote.responses.PokemonResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getRemotePokemonList(limit: String,offset: String): Response<PokemonListResponse>
    suspend fun getRemotePokemon(number: String): Response<PokemonResponse>
    suspend fun getRemoteEncounters(number: String): Response<List<EncountersListResponse>>

    suspend fun getRemoteAbilitiesInfo(number: String): Response<AbilityResponse>

}