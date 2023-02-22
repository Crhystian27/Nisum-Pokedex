package co.nisum.basicpokedex.domain.usescases

import co.nisum.basicpokedex.base.BaseUseCase
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.presentation.models.EncountersListPresentation
import javax.inject.Inject

class GetEncountersUseCase @Inject constructor(private val repository: IPokedexRepository)
    :BaseUseCase<GetEncountersUseCase.Params, List<EncountersListPresentation>>(){

    data class Params(
        val number: String)

    override suspend fun execute(params: Params): List<EncountersListPresentation> =
        repository.getEncountersList(params.number)



}