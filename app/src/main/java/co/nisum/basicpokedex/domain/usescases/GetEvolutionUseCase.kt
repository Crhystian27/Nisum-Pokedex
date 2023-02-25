package co.nisum.basicpokedex.domain.usescases

import co.nisum.basicpokedex.base.BaseUseCase
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.presentation.models.EvolutionPresentation
import javax.inject.Inject

class GetEvolutionUseCase @Inject constructor(private val repository: IPokedexRepository)
    :BaseUseCase<GetEvolutionUseCase.Params, EvolutionPresentation>(){

    data class Params(
        val species: String)

    override suspend fun execute(params: Params): EvolutionPresentation =
        repository.getEvolution(params.species)



}