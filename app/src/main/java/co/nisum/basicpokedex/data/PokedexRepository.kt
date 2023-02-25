package co.nisum.basicpokedex.data

import android.content.Context
import co.nisum.basicpokedex.data.mappers.toEncountersPresentation
import co.nisum.basicpokedex.data.mappers.toPresentation
import co.nisum.basicpokedex.data.remote.responses.PokemonListResponse
import co.nisum.basicpokedex.data.repository.dataSource.PokemonLocalDataSource
import co.nisum.basicpokedex.data.repository.dataSource.PokemonRemoteDataSource

import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.presentation.models.*
import co.nisum.basicpokedex.utils.extractNumberFromUrl
import co.nisum.basicpokedex.utils.isNetworkAvailable
import co.nisum.basicpokedex.utils.toCapitalize
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first

import retrofit2.Response
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : IPokedexRepository {



    override suspend fun getPokemonList(limit: String, offset: String): List<PokemonListPresentation> {
        return if (isNetworkAvailable(context)) {
            saveLocalPokemonList(responseToObjectResponse(pokemonRemoteDataSource.getRemotePokemonList(limit, offset)))
        } else {
            pokemonLocalDataSource.getPokemonListDB().first().toPresentation()
        }
    }

    override suspend fun getAbilitiesInfo(number: String): AbilityPresentation =
        responseToObjectResponse(pokemonRemoteDataSource.getRemoteAbilitiesInfo(number)).toPresentation()


    override suspend fun getLocationList(number: String): List<LocationPresentation> =
        responseToObjectResponse(pokemonRemoteDataSource.getRemoteLocation(number)).toEncountersPresentation()

    override suspend fun getEvolution(species: String): EvolutionPresentation =
       responseToObjectResponse(
           pokemonRemoteDataSource.getRemoteEvolution(
               responseToObjectResponse(
                   pokemonRemoteDataSource.getRemoteSpecies(species))
                   .evolution_chain.url.extractNumberFromUrl())).toPresentation()



    override suspend fun getPokemon(name: String): PokemonPresentation =
        responseToObjectResponse(pokemonRemoteDataSource.getRemotePokemon(name)).toPresentation()

    private fun <T> responseToObjectResponse(response: Response<T>): T {
        if (response.isSuccessful) {
            response.body()?.let { return it }
        }
        throw Exception("Body is null")
    }

    override suspend fun saveLocalPokemonList(pokemonList: PokemonListResponse): List<PokemonListPresentation> =
        pokemonLocalDataSource.savePokemonListToDB(context,pokemonList)

}