package co.nisum.basicpokedex.data.mappers

import co.nisum.basicpokedex.data.local.entity.PokemonListEntity
import co.nisum.basicpokedex.data.remote.responses.Results



fun Results.toPokemonListEntity() = PokemonListEntity(
    pokemonName = name,
    imageUrl = url )

fun List<Results>.toListPokemonListEntity() = map{ entity ->
    entity.toPokemonListEntity() }




/*fun PokemonListEntity.toPokemonList() = PokemonList(
    pokemonName = pokemonName,
    imageUrl = imageUrl,
    number= id )

fun List<PokemonListEntity>.toListPokemonList() = map { entity ->
    entity.toPokemonList() }
*/