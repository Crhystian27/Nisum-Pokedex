package co.nisum.basicpokedex.presentation.models

import android.os.Parcelable
import co.nisum.basicpokedex.data.remote.responses.Abilities
import co.nisum.basicpokedex.data.remote.responses.Moves
import co.nisum.basicpokedex.data.remote.responses.Stats
import co.nisum.basicpokedex.data.remote.responses.Types
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonPresentation(
    val id: Int,
    val abilities: List<Abilities>,
    val location_area_encounters: String,
    val moves: List<Moves>,
    val name: String,
    val stats: List<Stats>,
    val types: List<Types>,
): Parcelable

fun <T> equals(oldItem: T, newItem: T): Boolean =
    oldItem == newItem
