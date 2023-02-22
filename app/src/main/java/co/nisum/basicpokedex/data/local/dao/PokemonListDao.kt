package co.nisum.basicpokedex.data.local.dao

import androidx.room.*
import co.nisum.basicpokedex.data.local.entity.PokemonListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonListEntity(pokemonList : List<PokemonListEntity>)

    @Query("DELETE FROM PokemonListEntity")
    suspend fun deleteAllPokemonlist()

    @Update
    suspend fun updatePokemonListEntity(pokemonList : List<PokemonListEntity>)

    @Query("SELECT * FROM PokemonListEntity")
    fun getPokemonListEntity(): Flow<List<PokemonListEntity>>

}