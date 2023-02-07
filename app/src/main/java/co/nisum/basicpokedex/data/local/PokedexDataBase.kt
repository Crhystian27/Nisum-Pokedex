package co.nisum.basicpokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.nisum.basicpokedex.data.converter.Converters
import co.nisum.basicpokedex.data.local.dao.PokemonDao
import co.nisum.basicpokedex.data.local.dao.PokemonListDao
import co.nisum.basicpokedex.data.local.entity.PokemonEntity
import co.nisum.basicpokedex.data.local.entity.PokemonListEntity


@Database(
    entities = [PokemonListEntity::class, PokemonEntity::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class PokedexDataBase : RoomDatabase() {

    abstract val pokemonListDao : PokemonListDao
    abstract val pokemonDao : PokemonDao
}