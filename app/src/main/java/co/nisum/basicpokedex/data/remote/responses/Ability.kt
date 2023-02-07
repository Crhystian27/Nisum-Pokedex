package co.nisum.basicpokedex.data.remote.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ability(
    val name: String,
    val url: String
): Parcelable