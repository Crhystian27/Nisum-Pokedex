package co.nisum.basicpokedex.data.mappers

import co.nisum.basicpokedex.data.local.entity.PokemonListEntity
import co.nisum.basicpokedex.data.remote.responses.AbilityResponse
import co.nisum.basicpokedex.data.remote.responses.EncountersListResponse
import co.nisum.basicpokedex.data.remote.responses.PokemonResponse
import co.nisum.basicpokedex.data.remote.responses.ResultsResponse
import co.nisum.basicpokedex.presentation.models.AbilityPresentation
import co.nisum.basicpokedex.presentation.models.EncountersListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonPresentation


fun ResultsResponse.toEntity(id: Int? = null) = PokemonListEntity( id,name, url)
fun List<ResultsResponse>.toEntity() = map { it.toEntity() }


fun PokemonListEntity.toPresentation() = PokemonListPresentation(id, name, url)

fun List<PokemonListEntity>.toPresentation() = map { it.toPresentation() }


fun PokemonResponse.toPresentation() = PokemonPresentation(id, abilities, location_area_encounters, moves, name, stats,types)

fun EncountersListResponse.toPresentation() = EncountersListPresentation(location_area)

fun List<EncountersListResponse>.toEncountersPresentation() = map {
    it.toPresentation()
}

fun AbilityResponse.toPresentation() = AbilityPresentation(effect_entries)




