package co.nisum.basicpokedex.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListPresentation(
    val id: Int?,
    val name: String,
    val url: String
) : Parcelable


fun equals(oldItem: PokemonListPresentation, newItem: PokemonListPresentation): Boolean =
    oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.url == newItem.url