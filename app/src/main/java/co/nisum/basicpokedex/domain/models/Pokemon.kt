package co.nisum.basicpokedex.domain.models


import co.nisum.basicpokedex.data.remote.responses.Abilities
import co.nisum.basicpokedex.data.remote.responses.Moves
import co.nisum.basicpokedex.data.remote.responses.Types

data class Pokemon (
    val abilities: List<Abilities>,
    val location_area_encounters: String,
    val moves: List<Moves>,
    val name: String,
    val types: List<Types>,
    val number: Int
    )