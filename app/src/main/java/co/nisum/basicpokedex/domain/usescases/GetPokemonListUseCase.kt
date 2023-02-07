package co.nisum.basicpokedex.domain.usescases


import androidx.lifecycle.LiveData
import co.nisum.basicpokedex.base.BaseUseCase
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.utils.Resource
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response

import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private  val repository: IPokedexRepository)
    :BaseUseCase<GetPokemonListUseCase.Params, Response<PokemonListResponse>>() {

        data class Params(
            val limit: String,
            val offset: String)

    override suspend fun execute(params: Params): Response<PokemonListResponse> =
        repository.getRemotePokemonList(params.limit,params.offset)

}