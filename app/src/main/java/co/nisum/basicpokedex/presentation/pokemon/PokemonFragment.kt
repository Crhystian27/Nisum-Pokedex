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
import co.nisum.basicpokedex.data.remote.responses.*
import co.nisum.basicpokedex.databinding.FragmentPokemonBinding
import co.nisum.basicpokedex.presentation.PokedexViewModel
import co.nisum.basicpokedex.presentation.models.LocationPresentation
import co.nisum.basicpokedex.presentation.models.PokemonPresentation
import co.nisum.basicpokedex.presentation.pokemon.adapter.*
import co.nisum.basicpokedex.utils.*

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



            imgPokemonDetail.loadImage(
                progressPokemonDetail,
                coordinatorPokemonDetail,
                GradientDrawable.Orientation.TOP_BOTTOM,
                BuildConfig.BASE_URL_IMG + "/${number}.png"
            )


        }
    }

    override fun setListeners() {
        binding.apply {

            val stats = getString(R.string.string_stats)
            val moves = getString(R.string.string_moves)
            val evolution = getString(R.string.string_evolution)
            val location = getString(R.string.string_location)

            tlPokemonDetail.addTabs(
                listOf(
                    stats,
                    moves,
                    location,
                    evolution
                )
            )


            tlPokemonDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    when (tab.text) {
                        stats -> {
                            abilityLayout.consAbility.visibility = View.VISIBLE
                        }
                        moves -> {
                            constMoves.visibility = View.VISIBLE
                        }
                        location -> {
                            textLocation.text = getString(R.string.string_location_empty)
                            constLocation.visibility = View.VISIBLE
                        }
                        evolution -> {
                            rvEvolution.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    when (tab.text) {
                        stats -> {
                            abilityLayout.consAbility.visibility = View.GONE
                        }
                        moves -> {
                            constMoves.visibility = View.GONE
                        }
                        location -> {
                            constLocation.visibility = View.GONE
                        }
                        evolution -> {
                            rvEvolution.visibility = View.GONE
                        }
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}

            })
        }
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

    private fun setLocation(location: List<LocationPresentation>){
        with(binding.rvLocation){
            if(adapter == null){
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = PokemonLocationAdapter()
            }
            (adapter as? PokemonLocationAdapter)?.submitList(location)
        }
    }

    private fun setEvolution(species: List<Species>){
        with(binding.rvEvolution){
            if(adapter == null){
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = PokemonEvolutionAdapter()
            }
            (adapter as? PokemonEvolutionAdapter)?.submitList(species)
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
            viewModel.getLocation(pokemon.name)
            viewModel.getEvolution(pokemon.name)
        }
    }

    override fun observe() {
        viewModel.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                is PokedexEvent.PokemonEvent -> {
                    setPokemon(event.pokemonPresentation)
                }
                is PokedexEvent.PokemonLocationList ->{
                    if(event.locationPresentation.isNotEmpty()){
                        setLocation(event.locationPresentation)
                        binding.constLocationEmpty.visibility = View.GONE
                    }else {
                        binding.constLocationEmpty.visibility = View.VISIBLE
                    }
                }
                is PokedexEvent.PokemonEvolution -> {
                    val chain = event.evolutionPresentation.chain
                    val firstSpecies = chain.species
                    val secondSpecies = chain.evolves_to.firstOrNull()?.species
                    val thirdSpecies = chain.evolves_to.firstOrNull()?.evolves_to?.firstOrNull()?.species
                    setEvolution(listOfNotNull(firstSpecies, secondSpecies, thirdSpecies))
                }
                else -> {}
            }
        }
    }
}