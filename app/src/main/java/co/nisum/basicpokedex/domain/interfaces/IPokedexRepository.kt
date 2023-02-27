package co.nisum.basicpokedex.domain.interfaces


import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.presentation.models.*


interface IPokedexRepository {

    suspend fun getPokemonList(limit: String, offset: String): List<PokemonListPresentation>
    suspend fun getLocationList(number: String): List<LocationPresentation>
    suspend fun getEvolution(species: String): EvolutionPresentation
    suspend fun getPokemon(name: String): PokemonPresentation
    suspend fun saveLocalPokemonList(pokemonList: PokemonListResponse): List<PokemonListPresentation>

}