package co.nisum.basicpokedex.data.repository.dataSourceImpl

import android.content.Context
import co.nisum.basicpokedex.data.local.dao.PokemonListDao
import co.nisum.basicpokedex.data.local.entity.PokemonListEntity
import co.nisum.basicpokedex.data.mappers.toEntity
import co.nisum.basicpokedex.data.mappers.toPresentation
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.data.repository.dataSource.PokemonLocalDataSource
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.utils.save
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor (
    private val pokemonListDao: PokemonListDao,
): PokemonLocalDataSource {

    override fun getPokemonListDB(): Flow<List<PokemonListEntity>> =
        pokemonListDao.getPokemonListEntity()

    override suspend fun savePokemonListToDB(context: Context, pokemonList: PokemonListResponse): List<PokemonListPresentation> {
        context.save("Next",pokemonList.next)
        val existingPokemonList = pokemonListDao.getPokemonListEntity().first()
        val pokemonListResponse = pokemonList.results.toEntity()

        return if(existingPokemonList.isEmpty()){
            pokemonListDao.insertPokemonListEntity(pokemonListResponse)
            getPokemonListDB().first().toPresentation()
        }else {
            pokemonListDao.insertPokemonListEntity((existingPokemonList+ pokemonListResponse).distinctBy { it.name })
            getPokemonListDB().first().toPresentation()
        }
    }
}