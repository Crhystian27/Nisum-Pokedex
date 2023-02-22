package co.nisum.basicpokedex.domain.interfaces


import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.presentation.models.AbilityPresentation
import co.nisum.basicpokedex.presentation.models.EncountersListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonPresentation


interface IPokedexRepository {

    suspend fun getPokemonList(limit: String, offset: String): List<PokemonListPresentation>
    suspend fun getAbilitiesInfo(number: String): AbilityPresentation
    suspend fun getEncountersList(number: String): List<EncountersListPresentation>
    suspend fun getPokemon(name: String): PokemonPresentation
    suspend fun saveLocalPokemonList(pokemonList: PokemonListResponse): List<PokemonListPresentation>

}