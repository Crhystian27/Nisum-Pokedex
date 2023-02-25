package co.nisum.basicpokedex.presentation.models

import android.os.Parcelable
import co.nisum.basicpokedex.data.remote.responses.*
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
    val species: Child,
): Parcelable

fun <T> equals(oldItem: T, newItem: T): Boolean =
    oldItem == newItem
