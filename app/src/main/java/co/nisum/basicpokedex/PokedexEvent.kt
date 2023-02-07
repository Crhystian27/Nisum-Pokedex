package co.nisum.basicpokedex

import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonPresentation
import com.google.gson.JsonObject


sealed class PokedexEvent{
    class PokemonErrorEvent(val error: String?): PokedexEvent()
    class PokemonListEvent(val pokemonListPresentation: List<PokemonListPresentation>): PokedexEvent()
    class PokemonEvent(val pokemon: PokemonPresentation): PokedexEvent()
}