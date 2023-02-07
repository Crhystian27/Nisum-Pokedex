package co.nisum.basicpokedex.data.repository.dataSource

import co.nisum.basicpokedex.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    suspend fun savePokemonToDB(pokemon: Pokemon)
    fun getLocalPokemonList(): Flow<List<Pokemon>>
    suspend fun deletePokemonFromDB(pokemon: Pokemon)


}