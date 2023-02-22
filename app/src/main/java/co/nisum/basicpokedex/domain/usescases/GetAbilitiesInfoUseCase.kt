package co.nisum.basicpokedex.domain.usescases

import co.nisum.basicpokedex.base.BaseUseCase
import co.nisum.basicpokedex.domain.interfaces.IPokedexRepository
import co.nisum.basicpokedex.presentation.models.AbilityPresentation
import javax.inject.Inject

class GetAbilitiesInfoUseCase @Inject constructor(private val repository: IPokedexRepository)
    :BaseUseCase<GetAbilitiesInfoUseCase.Params,AbilityPresentation>(){

    data class Params(
        val number: String)

    override suspend fun execute(params: Params): AbilityPresentation =
        repository.getAbilitiesInfo(params.number)

}