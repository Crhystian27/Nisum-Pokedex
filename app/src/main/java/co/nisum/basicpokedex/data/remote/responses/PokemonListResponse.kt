package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListResponse(
    val count: Int,
    val previous: String,
    val next: String,
    val results: List<Results>
):Parcelable