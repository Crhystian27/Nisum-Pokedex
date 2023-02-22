package co.nisum.basicpokedex.data.repository.dataSourceImpl

import co.nisum.basicpokedex.data.remote.PokedexApiInterface
import co.nisum.basicpokedex.data.remote.responses.AbilityResponse
import co.nisum.basicpokedex.data.remote.responses.EncountersListResponse
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.data.remote.responses.PokemonResponse
import co.nisum.basicpokedex.data.repository.dataSource.PokemonRemoteDataSource
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokedexApiInterface: PokedexApiInterface
):PokemonRemoteDataSource {

    override suspend fun getRemotePokemonList(limit: String, offset: String): Response<PokemonListResponse> =
        pokedexApiInterface.getRemotePokemonList(limit,offset)


    override suspend fun getRemotePokemon(number: String): Response<PokemonResponse> =
        pokedexApiInterface.getPokemon(number)

    override suspend fun getRemoteEncounters(number: String): Response<List<EncountersListResponse>> =
        pokedexApiInterface.getPokemonPlaceToFind(number)

    override suspend fun getRemoteAbilitiesInfo(number: String): Response<AbilityResponse> =
        pokedexApiInterface.getAbilityPokemonInfo(number)



}