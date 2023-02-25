package co.nisum.basicpokedex

import co.nisum.basicpokedex.presentation.models.*


sealed class PokedexEvent{

    class PokemonListEvent(val pokemonListPresentation: List<PokemonListPresentation>): PokedexEvent()
    class PokemonEvent(val pokemonPresentation: PokemonPresentation): PokedexEvent()
    class PokemonEventAbilities(val abilities: AbilityPresentation): PokedexEvent()
    class PokemonLocationList(val locationPresentation: List<LocationPresentation>) : PokedexEvent()
    class PokemonEvolution(val evolutionPresentation: EvolutionPresentation): PokedexEvent()
}