package co.nisum.basicpokedex.presentation

import co.nisum.basicpokedex.PokedexEvent
import co.nisum.basicpokedex.base.BaseViewModel
import co.nisum.basicpokedex.domain.usescases.GetAbilitiesInfoUseCase
import co.nisum.basicpokedex.domain.usescases.GetEncountersUseCase
import co.nisum.basicpokedex.domain.usescases.GetPokemonListUseCase
import co.nisum.basicpokedex.domain.usescases.GetPokemonUseCase
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.utils.LogError
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getEncountersUseCase: GetEncountersUseCase,
    private val getAbilitiesInfoUseCase: GetAbilitiesInfoUseCase
) : BaseViewModel<PokedexEvent>() {

    private var currentList: List<PokemonListPresentation>  = emptyList()


    fun getPokemonList(limit: String, offset: String) {
        executeUseCase(
            { getPokemonListUseCase.execute(GetPokemonListUseCase.Params(limit, offset)) },
            { result ->
                currentList = result
                _event.value = PokedexEvent.PokemonListEvent(result) },
            { error -> "$error".LogError() }
        )
    }


    fun getPokemon(name: String){
        executeUseCase(
            {getPokemonUseCase.execute(GetPokemonUseCase.Params(name))},
            {result -> _event.value = PokedexEvent.PokemonEvent(result)},
            {error -> "$error".LogError()}
        )
    }


    fun getAbilitiesInfo(number: String){
        executeUseCase(
            {getAbilitiesInfoUseCase.execute(GetAbilitiesInfoUseCase.Params(number))},
            {result -> _event.value = PokedexEvent.PokemonEventAbilities(result)},
            {error -> "$error".LogError()}
        )
    }


    fun searchInPokemonList(searchText: String?,filterList: List<PokemonListPresentation>?){
        if(searchText.isNullOrEmpty()){
            _event.value = PokedexEvent.PokemonListEvent(currentList)

        }else{
            val pokemon = filterList ?: currentList
            searchText.lowercase().also { search ->
                _event.value = PokedexEvent.PokemonListEvent(
                    pokemon.filter {
                        it.name.lowercase().contains(search)
                    }
                )
            }
        }
    }


}