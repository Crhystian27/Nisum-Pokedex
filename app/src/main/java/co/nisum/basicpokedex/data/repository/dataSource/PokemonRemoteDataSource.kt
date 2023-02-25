package co.nisum.basicpokedex.data.repository.dataSource

import co.nisum.basicpokedex.data.remote.responses.*
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getRemotePokemonList(limit: String,offset: String): Response<PokemonListResponse>
    suspend fun getRemotePokemon(number: String): Response<PokemonResponse>
    suspend fun getRemoteSpecies(species: String): Response<SpeciesResponse>
    suspend fun getRemoteEvolution(number: String): Response<EvolutionResponse>
    suspend fun getRemoteLocation(number: String): Response<List<LocationResponse>>
    suspend fun getRemoteAbilitiesInfo(number: String): Response<AbilityResponse>

}