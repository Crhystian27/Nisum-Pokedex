package co.nisum.basicpokedex.data.repository.dataSourceImpl

import co.nisum.basicpokedex.data.remote.PokedexApiInterface
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.data.repository.dataSource.PokemonRemoteDataSource
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokedexApiInterface: PokedexApiInterface
):PokemonRemoteDataSource {


    override suspend fun getRemotePokemonList(limit: String, offset: String): Response<PokemonListResponse> {
        return pokedexApiInterface.getRemotePokemonList(limit, offset)
    }

}