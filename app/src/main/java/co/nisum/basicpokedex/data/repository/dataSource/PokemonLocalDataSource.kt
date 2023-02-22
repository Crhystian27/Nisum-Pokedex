package co.nisum.basicpokedex.data.repository.dataSource

import android.content.Context
import co.nisum.basicpokedex.data.local.entity.PokemonListEntity
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    fun getPokemonListDB(): Flow<List<PokemonListEntity>>
    suspend fun savePokemonListToDB(context: Context, pokemonList: PokemonListResponse): List<PokemonListPresentation>


}