package co.nisum.basicpokedex.domain.usescases


import co.nisum.basicpokedex.base.BaseUseCase
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation

import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private  val repository: IPokedexRepository)
    :BaseUseCase<GetPokemonListUseCase.Params, List<PokemonListPresentation>>() {

        data class Params(
            val limit: String,
            val offset: String
            )

    override suspend fun execute(params: Params): List<PokemonListPresentation> =
        repository.getPokemonList(params.limit,params.offset)

}