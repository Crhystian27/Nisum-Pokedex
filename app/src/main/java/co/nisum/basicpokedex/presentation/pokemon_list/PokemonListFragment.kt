package co.nisum.basicpokedex.presentation.pokemon_list

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.nisum.basicpokedex.PokedexEvent
import co.nisum.basicpokedex.R
import co.nisum.basicpokedex.base.BaseActivity
import co.nisum.basicpokedex.base.BaseFragment
import co.nisum.basicpokedex.databinding.FragmentPokemonListBinding
import co.nisum.basicpokedex.presentation.PokedexViewModel
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.pokemon_list.adapter.PokemonClick
import co.nisum.basicpokedex.presentation.pokemon_list.adapter.PokemonListAdapter

import co.nisum.basicpokedex.utils.isNetworkAvailable
import co.nisum.basicpokedex.utils.load
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding, PokedexViewModel>(), PokemonClick {

    override val viewModel: PokedexViewModel by viewModels()

    private var pressedTime: Long = 0

    private var isScrolling = false

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPokemonListBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemonList("151","0")
    }

    override fun setUI() {
        binding.apply {
            context?.getColor(R.color.white)
                ?.let { constraintPokemonList.setBackgroundColor(it) }
        }
    }


    private fun setAdapter(results: List<PokemonListPresentation>){
        with(binding.rvPokemon){
            if(adapter == null){
                layoutManager = GridLayoutManager(
                    context,
                2,
                )

                adapter = PokemonListAdapter(this@PokemonListFragment)
                addOnScrollListener(this@PokemonListFragment.onScrollListener)
            }
            (adapter as? PokemonListAdapter)?.submitList(results)
        }
    }

    private val onScrollListener = object: RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvPokemon.layoutManager as GridLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = hasReachedToEnd && isScrolling
            if(shouldPaginate){

                if(isNetworkAvailable(context)){
                    val uri = Uri.parse(context?.load("Next"))
                    val offset = uri.getQueryParameter("offset")!!
                    val limit = uri.getQueryParameter("limit")!!

                    viewModel.getPokemonList(limit,offset)
                    isScrolling = false
                }

            }
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

        binding.apply {
            var delete = false
            tietSearchPokemon.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    delete = before > count
                }

                override fun afterTextChanged(s: Editable?) {
                    if(delete)
                        viewModel.searchInPokemonList(s?.toString(), emptyList())
                    else
                        viewModel.searchInPokemonList(s?.toString(),
                            (rvPokemon.adapter as? PokemonListAdapter)?.currentList
                        )
                }

            })
        }

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