package co.nisum.basicpokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import co.nisum.basicpokedex.data.local.dao.PokemonListDao
import co.nisum.basicpokedex.data.local.entity.PokemonListEntity


@Database(
    entities = [PokemonListEntity::class],
    version = 1,
    exportSchema = false
)

abstract class PokedexDataBase : RoomDatabase() {

    abstract val pokemonListDao : PokemonListDao
}