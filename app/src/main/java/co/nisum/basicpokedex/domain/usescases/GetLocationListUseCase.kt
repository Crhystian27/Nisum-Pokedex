package co.nisum.basicpokedex.domain.usescases

import co.nisum.basicpokedex.base.BaseUseCase
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.presentation.models.LocationPresentation
import javax.inject.Inject

class GetLocationListUseCase @Inject constructor(private val repository: IPokedexRepository)
    :BaseUseCase<GetLocationListUseCase.Params, List<LocationPresentation>>(){

    data class Params(
        val number: String)

    override suspend fun execute(params: Params): List<LocationPresentation> =
        repository.getLocationList(params.number)



}