package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EvolutionResponse(
    val chain: Chain
): Parcelable

@Parcelize
data class Chain(
    val evolves_to: List<EvolvesTo>,
    val species: Species
): Parcelable

@Parcelize
data class EvolvesTo(
    val evolves_to: List<EvolvesTo>,
    val species: Species
): Parcelable

@Parcelize
data class Species(
    val name: String,
    val url: String
): Parcelable