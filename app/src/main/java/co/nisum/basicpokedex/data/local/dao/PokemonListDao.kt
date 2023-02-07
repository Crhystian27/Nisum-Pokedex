package co.nisum.basicpokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.nisum.basicpokedex.data.local.entity.PokemonListEntity

@Dao
interface PokemonListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemonListEntity(pokemonList : List<PokemonListEntity>)

    @Query("DELETE FROM PokemonListEntity")
    suspend fun deleteAllPokemonlist()

    @Query("SELECT * FROM PokemonListEntity")
    suspend fun getAllPokemonListEntity():List<PokemonListEntity>

}