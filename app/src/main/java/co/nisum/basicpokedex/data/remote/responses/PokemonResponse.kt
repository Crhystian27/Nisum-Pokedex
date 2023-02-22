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
    val stats: List<Stats>,
    val types: List<Types>,
    ): Parcelable

@Parcelize
data class Moves(val move: Child) :Parcelable
@Parcelize
data class Types(val type: Child) : Parcelable
@Parcelize
data class Abilities(val ability: Child) :Parcelable
@Parcelize
data class Stats(
    val base_stat: String,
    val stat: Child) :Parcelable

@Parcelize
data class Child(
    val name: String,
    val url: String
): Parcelable
