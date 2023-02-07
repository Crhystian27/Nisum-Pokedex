package co.nisum.basicpokedex.presentation.models

data class PokemonListPresentation(
    val pokemonName: String,
    val imageUrl: String)

fun equals(oldItem: PokemonListPresentation, newItem: PokemonListPresentation): Boolean =
    oldItem.pokemonName == newItem.pokemonName && oldItem.imageUrl == newItem.imageUrl