package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListResponse(
    val next: String,
    val results: List<ResultsResponse>
): Parcelable

@Parcelize
data class ResultsResponse(
    val name: String,
    val url: String
):Parcelable