package co.nisum.basicpokedex.presentation.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.nisum.basicpokedex.PokedexEvent
import co.nisum.basicpokedex.base.BaseFragment
import co.nisum.basicpokedex.databinding.FragmentPokemonBinding
import co.nisum.basicpokedex.presentation.PokedexViewModel
import co.nisum.basicpokedex.presentation.models.PokemonPresentation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonFragment : BaseFragment<FragmentPokemonBinding, PokedexViewModel>(){

    var number : String? = ""
    override val viewModel: PokedexViewModel by viewModels()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentPokemonBinding.inflate(inflater,container,false)

    override fun getBundleArgs() {
        arguments?.let {
            number = it.getString("number")
        }
    }

    private fun setPokemon(pokemon: PokemonPresentation){


    }

    override fun setListeners() {

    }

    override fun observe() {
        viewModel.event.observe(viewLifecycleOwner){ event ->
            when(event){
                is PokedexEvent.PokemonEvent -> {
                    //setPokemon(event.po)
                }
                else ->{}
            }
        }
    }
}