package co.nisum.basicpokedex.presentation.pokemon

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.nisum.basicpokedex.BuildConfig
import co.nisum.basicpokedex.PokedexEvent
import co.nisum.basicpokedex.R
import co.nisum.basicpokedex.base.BaseFragment
import co.nisum.basicpokedex.data.remote.responses.Abilities
import co.nisum.basicpokedex.data.remote.responses.Moves
import co.nisum.basicpokedex.data.remote.responses.Types
import co.nisum.basicpokedex.databinding.FragmentPokemonBinding
import co.nisum.basicpokedex.presentation.PokedexViewModel
import co.nisum.basicpokedex.presentation.models.PokemonPresentation
import co.nisum.basicpokedex.presentation.pokemon.adapter.PokemonAbilitiesAdapter
import co.nisum.basicpokedex.presentation.pokemon.adapter.PokemonMoveAdapter
import co.nisum.basicpokedex.presentation.pokemon.adapter.PokemonTypeAdapter
import co.nisum.basicpokedex.utils.addTabs

import co.nisum.basicpokedex.utils.isNetworkAvailable
import co.nisum.basicpokedex.utils.loadImage
import co.nisum.basicpokedex.utils.toCapitalize
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonFragment : BaseFragment<FragmentPokemonBinding, PokedexViewModel>() {

    var number: String? = ""
    override val viewModel: PokedexViewModel by viewModels()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPokemonBinding.inflate(inflater, container, false)

    override fun getBundleArgs() {
        arguments?.let {
            number = it.getString("number")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isNetworkAvailable(context)) {
            number?.let { viewModel.getPokemon(it) }
        }
    }


    override fun setUI() {
        binding.apply {
            val stats = getString(R.string.string_stats)
            val moves = getString(R.string.string_moves)
            val evolution = getString(R.string.string_evolution)

            imgPokemonDetail.loadImage(
                progressPokemonDetail,
                coordinatorPokemonDetail,
                GradientDrawable.Orientation.TOP_BOTTOM,
                BuildConfig.BASE_URL_IMG + "/${number}.png"
            )

            tlPokemonDetail.addTabs(
                listOf(
                    stats,
                    moves,
                    evolution
                )
            )

            tlPokemonDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    when (tab.text) {
                        stats -> { binding.abilityLayout.consAbility.visibility = View.VISIBLE
                        binding.rvMoves.visibility = View.GONE}
                        moves -> {binding.abilityLayout.consAbility.visibility = View.GONE
                            binding.rvMoves.visibility = View.VISIBLE}
                        evolution -> {binding.abilityLayout.consAbility.visibility = View.GONE
                            binding.rvMoves.visibility = View.GONE}
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}

            })
        }
    }

    override fun setListeners() {

    }

    private fun setTypeAdapter(types: List<Types>) {
        with(binding.rvTypePokemonDetail) {
            if (adapter == null) {

                layoutManager = if (types.count() > 1) {
                    GridLayoutManager(
                        context,
                        2,
                    )
                } else {
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                }


                adapter = PokemonTypeAdapter()
            }

            (adapter as? PokemonTypeAdapter)?.submitList(types)
        }
    }

    private fun setAbilities(abilities: List<Abilities>) {
        with(binding.abilityLayout.rvAbility) {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = PokemonAbilitiesAdapter()
            }
            (adapter as? PokemonAbilitiesAdapter)?.submitList(abilities)
        }
    }

    private fun setMoves(moves: List<Moves>){
        with(binding.rvMoves){
            if(adapter == null){
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = PokemonMoveAdapter()
            }
            (adapter as? PokemonMoveAdapter)?.submitList(moves)
        }

    }


    private fun setPokemon(pokemon: PokemonPresentation) {
        binding.apply {
            titlePokemonDetail.text = "#${pokemon.id}  ${pokemon.name.toCapitalize()}"
            abilityLayout.hp.text = pokemon.stats[0].stat.name.toCapitalize()+"\n"+ pokemon.stats[0].base_stat
            abilityLayout.attack.text = pokemon.stats[1].stat.name.toCapitalize()+"\n"+ pokemon.stats[1].base_stat
            abilityLayout.defense.text = pokemon.stats[2].stat.name.toCapitalize()+"\n"+ pokemon.stats[2].base_stat
            abilityLayout.specialattack.text = pokemon.stats[3].stat.name.toCapitalize().replace("-"," ")+"\n"+ pokemon.stats[3].base_stat
            abilityLayout.specialdefense.text = pokemon.stats[4].stat.name.toCapitalize().replace("-"," ")+"\n"+ pokemon.stats[4].base_stat
            abilityLayout.speed.text = pokemon.stats[5].stat.name.toCapitalize()+"\n"+ pokemon.stats[5].base_stat
            abilityLayout.titleAbility.text = "Abilities (${pokemon.abilities.size})"
            setTypeAdapter(pokemon.types)
            setAbilities(pokemon.abilities)
            setMoves(pokemon.moves)

        }
    }

    override fun observe() {
        viewModel.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                is PokedexEvent.PokemonEvent -> {
                    setPokemon(event.pokemonPresentation)
                }
                else -> {}
            }
        }
    }
}