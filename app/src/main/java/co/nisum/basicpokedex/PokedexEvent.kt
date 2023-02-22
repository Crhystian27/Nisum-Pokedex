package co.nisum.basicpokedex

import co.nisum.basicpokedex.presentation.models.AbilityPresentation
import co.nisum.basicpokedex.presentation.models.EncountersListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonPresentation



sealed class PokedexEvent{

    class PokemonListEvent(val pokemonListPresentation: List<PokemonListPresentation>): PokedexEvent()
    class PokemonEvent(val pokemonPresentation: PokemonPresentation): PokedexEvent()
    class PokemonEventAbilities(val abilities: AbilityPresentation): PokedexEvent()
    class PokemonEncountersList(val encountersListPresentation: EncountersListPresentation) : PokedexEvent()
}