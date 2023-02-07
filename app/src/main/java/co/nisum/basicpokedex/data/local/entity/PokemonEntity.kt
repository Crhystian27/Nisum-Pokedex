package co.nisum.basicpokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import co.nisum.basicpokedex.data.converter.Converters
import co.nisum.basicpokedex.data.remote.responses.Abilities
import co.nisum.basicpokedex.data.remote.responses.Moves
import co.nisum.basicpokedex.data.remote.responses.Types

@Entity
class PokemonEntity(
    //@TypeConverters(Converters::class)
    //val abilities: List<Abilities>,
    val location_area_encounters: String,

    //val moves: List<Moves>,
    val name: String,

    //val types: List<Types>,
    val number: Int,
    @PrimaryKey val id: Int? = null)