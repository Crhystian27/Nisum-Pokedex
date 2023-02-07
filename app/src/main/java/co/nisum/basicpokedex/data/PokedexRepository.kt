package co.nisum.basicpokedex.data

import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.data.repository.dataSource.PokemonLocalDataSource
import co.nisum.basicpokedex.data.repository.dataSource.PokemonRemoteDataSource

import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository

import retrofit2.Response
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : IPokedexRepository {


    override suspend fun getRemotePokemonList(
        limit: String,
        offset: String
    ): Response<PokemonListResponse> {
        return pokemonRemoteDataSource.getRemotePokemonList(limit,offset)
    }




}