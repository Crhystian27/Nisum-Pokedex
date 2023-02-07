package co.nisum.basicpokedex.data.repository.dataSourceImpl

import co.nisum.basicpokedex.data.local.dao.PokemonDao
import co.nisum.basicpokedex.data.remote.PokedexApiInterface
import co.nisum.basicpokedex.data.repository.dataSource.PokemonLocalDataSource
import co.nisum.basicpokedex.data.repository.dataSource.PokemonRemoteDataSource
import co.nisum.basicpokedex.domain.models.Pokemon
import com.google.gson.JsonObject
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor (
    private val pokemonDao: PokemonDao
): PokemonLocalDataSource {


    override suspend fun savePokemonToDB(pokemon: Pokemon) {
        TODO("Not yet implemented")
    }

    override fun getLocalPokemonList(): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePokemonFromDB(pokemon: Pokemon) {
        TODO("Not yet implemented")
    }


}