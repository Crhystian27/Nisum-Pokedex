package co.nisum.basicpokedex.presentation.mappers

import co.nisum.basicpokedex.data.remote.responses.PokemonResponse
import co.nisum.basicpokedex.data.remote.responses.Results
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonPresentation

fun Results.toPokemonListPresentation() = PokemonListPresentation(
    pokemonName = name,
    imageUrl =  url)

fun List<Results>.toListPokemonListPresentation()= map { data ->
    data.toPokemonListPresentation()
}

fun PokemonResponse.toPokemonPresentation() = PokemonPresentation(
    id, abilities, location_area_encounters, moves, name, types )