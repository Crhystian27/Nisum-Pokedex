package co.nisum.basicpokedex.presentation.pokemon_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.nisum.basicpokedex.PokedexEvent
import co.nisum.basicpokedex.R
import co.nisum.basicpokedex.base.BaseActivity
import co.nisum.basicpokedex.base.BaseFragment
import co.nisum.basicpokedex.databinding.FragmentPokemonListBinding
import co.nisum.basicpokedex.presentation.PokedexViewModel
import co.nisum.basicpokedex.presentation.mappers.toListPokemonListPresentation
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.pokemon_list.adapter.PokemonClick
import co.nisum.basicpokedex.presentation.pokemon_list.adapter.PokemonListAdapter
import co.nisum.basicpokedex.utils.LogDebug
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding, PokedexViewModel>(), PokemonClick {

    override val viewModel: PokedexViewModel by viewModels()

    private var pressedTime: Long = 0

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPokemonListBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemonList("151","0")
    }


    override fun setUI() {

    }


    private fun setAdapter(pokemonList: List<PokemonListPresentation>){
        with(binding.rvPokemon){
            if(adapter == null){
                layoutManager = GridLayoutManager(
                    context,
                2,
                )

                adapter = PokemonListAdapter(this@PokemonListFragment)
            }
            (adapter as? PokemonListAdapter)?.submitList(pokemonList)
        }

    }

    override fun observe() {
        viewModel.event.observe(viewLifecycleOwner){event ->
            when(event){
                is PokedexEvent.PokemonListEvent ->{
                    setAdapter(event.pokemonListPresentation)
                }
                else -> {}
            }
        }
    }

    override fun setListeners() {

        /*binding.apply {
            var delete = false
            tietSearchPokemon.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    delete = before > count
                }

                override fun afterTextChanged(s: Editable?) {
                    if(delete)
                        viewModel.serachPokemon(s?.toString(), emptyList())
                    else
                        viewModel.serachPokemon(s?.toString(),
                            (rvPokemon.adapter as? PokemonListAdapter)?.currentList
                        )
                }

            })
        }*/

        handleBack()
    }

    private fun handleBack() {
        (requireActivity() as? BaseActivity<*>)?.hideBack()
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(pressedTime+ 2000 > System.currentTimeMillis()){
                        requireActivity().finish()
                    }else {
                        Toast.makeText(context, getString(R.string.back_pressed), Toast.LENGTH_SHORT).show()
                    }
                    pressedTime = System.currentTimeMillis()
                }
            })


    }

    override fun onClick(number: String) {
        findNavController().navigate(
            PokemonListFragmentDirections.actionPokemonListToPokemon(number)
        )
    }

}