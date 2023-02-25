package co.nisum.basicpokedex.data.mappers

import co.nisum.basicpokedex.data.local.entity.PokemonListEntity
import co.nisum.basicpokedex.data.remote.responses.*
import co.nisum.basicpokedex.presentation.models.*


fun ResultsResponse.toEntity(id: Int? = null) = PokemonListEntity( id,name, url)
fun List<ResultsResponse>.toEntity() = map { it.toEntity() }


fun PokemonListEntity.toPresentation() = PokemonListPresentation(id, name, url)

fun List<PokemonListEntity>.toPresentation() = map { it.toPresentation() }


fun PokemonResponse.toPresentation() = PokemonPresentation(id, abilities, location_area_encounters, moves, name, stats,types,species)

fun LocationResponse.toPresentation() = LocationPresentation(location_area)

fun List<LocationResponse>.toEncountersPresentation() = map {
    it.toPresentation()
}

fun EvolutionResponse.toPresentation()= EvolutionPresentation(chain)

fun AbilityResponse.toPresentation() = AbilityPresentation(effect_entries)




