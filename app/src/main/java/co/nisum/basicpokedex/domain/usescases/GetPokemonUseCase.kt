package co.nisum.basicpokedex.domain.usescases

import co.nisum.basicpokedex.base.BaseUseCase
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.presentation.models.PokemonPresentation
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: IPokedexRepository)
    :BaseUseCase<GetPokemonUseCase.Params, PokemonPresentation?>(){

    data class Params(
        val number: String)

    override suspend fun execute(params: Params): PokemonPresentation? {
        TODO("Not yet implemented")
    }


}