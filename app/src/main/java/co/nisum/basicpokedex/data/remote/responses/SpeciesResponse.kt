package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpeciesResponse(
    val evolution_chain: EvolutionChain
): Parcelable

@Parcelize
data class EvolutionChain(
    val url: String
): Parcelable