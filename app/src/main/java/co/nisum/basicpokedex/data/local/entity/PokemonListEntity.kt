package co.nisum.basicpokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonListEntity (
    val pokemonName: String,
    val imageUrl: String,
    @PrimaryKey val id: Int? = null)