package co.nisum.basicpokedex.data.repository.dataSource

import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getRemotePokemonList(limit: String, offset: String): Response<PokemonListResponse>
    //suspend fun getRemotePokemonSearch(name: String) : Observable<PokemonListResponse>

}