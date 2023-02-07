package co.nisum.basicpokedex.domain.interfaces



import androidx.lifecycle.LiveData
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.utils.Resource
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response


interface IPokedexRepository {

    suspend fun getRemotePokemonList(limit: String,offset: String): Response<PokemonListResponse>
}