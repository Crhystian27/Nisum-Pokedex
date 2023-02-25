package co.nisum.basicpokedex.data.repository.dataSourceImpl

import co.nisum.basicpokedex.data.remote.PokedexApiInterface
import co.nisum.basicpokedex.data.remote.responses.*
import co.nisum.basicpokedex.data.repository.dataSource.PokemonRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokedexApiInterface: PokedexApiInterface
):PokemonRemoteDataSource {

    override suspend fun getRemotePokemonList(limit: String, offset: String): Response<PokemonListResponse> =
        pokedexApiInterface.getRemotePokemonList(limit,offset)


    override suspend fun getRemotePokemon(number: String): Response<PokemonResponse> =
        pokedexApiInterface.getPokemon(number)

    override suspend fun getRemoteSpecies(species: String): Response<SpeciesResponse> =
        pokedexApiInterface.getSpeciesPokemon(species)

    override suspend fun getRemoteEvolution(number: String): Response<EvolutionResponse> =
       pokedexApiInterface.getEvolutionPokemon(number)

    override suspend fun getRemoteLocation(number: String): Response<List<LocationResponse>> =
        pokedexApiInterface.getLocationPokemon(number)

    override suspend fun getRemoteAbilitiesInfo(number: String): Response<AbilityResponse> =
        pokedexApiInterface.getAbilityPokemonInfo(number)



}