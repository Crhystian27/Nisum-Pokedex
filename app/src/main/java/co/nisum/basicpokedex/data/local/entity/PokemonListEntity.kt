package co.nisum.basicpokedex.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PokemonListEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val url: String
    ): Parcelable