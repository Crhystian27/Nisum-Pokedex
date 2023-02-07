package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonResponse(
    val id: Int,
    val abilities: List<Abilities>,
    val location_area_encounters: String,
    val moves: List<Moves>,
    val name: String,
    val types: List<Types>,
    ): Parcelable