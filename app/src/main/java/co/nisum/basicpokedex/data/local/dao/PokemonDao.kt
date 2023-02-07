package co.nisum.basicpokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.nisum.basicpokedex.data.local.entity.PokemonEntity

@Dao
interface PokemonDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonEntity(pokemon : PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getPokemonEntity(): PokemonEntity

}