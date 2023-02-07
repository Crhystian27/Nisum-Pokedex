package co.nisum.basicpokedex.presentation

import co.nisum.basicpokedex.PokedexEvent
import co.nisum.basicpokedex.base.BaseViewModel
import co.nisum.basicpokedex.domain.usescases.GetPokemonListUseCase
import co.nisum.basicpokedex.domain.usescases.GetPokemonUseCase
import co.nisum.basicpokedex.presentation.mappers.toListPokemonListPresentation
import co.nisum.basicpokedex.utils.LogError
import co.nisum.basicpokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonUseCase: GetPokemonUseCase
) : BaseViewModel<PokedexEvent>() {


    fun getPokemonList(limit: String, offset: String){
        executeUseCase(
            { getPokemonListUseCase.execute(GetPokemonListUseCase.Params(limit,offset)) },
            { result -> _event.value = result.body()?.let { PokedexEvent.PokemonListEvent(it.results.toListPokemonListPresentation()) } },
            { error -> "$error".LogError() }
        )
    }

    /*fun getPokemonInfo(number: String){
        executeUseCase(
            { getPokemonUseCase.execute(GetPokemonUseCase.Params(number))},
            { result -> _event.value = result?.let {
                PokedexEvent.PokemonEvent(it) } },
            { error -> "$error".LogError()}
        )
    }


    fun serachPokemon(searchText: String?, filterList: List<PokemonListPresentation>?){
        if(searchText.isNullOrEmpty()){
            _event.value = PokedexEvent.PokemonListEvent(searchPokemonList)

        }else{
            val pokemon = filterList ?: searchPokemonList
            searchText.lowercase().also { search ->
                _event.value = PokedexEvent.PokemonListEvent(
                    pokemon.filter {
                        it.pokemonName.lowercase().contains(search)
                    }
                )
            }
        }


    }*/





}