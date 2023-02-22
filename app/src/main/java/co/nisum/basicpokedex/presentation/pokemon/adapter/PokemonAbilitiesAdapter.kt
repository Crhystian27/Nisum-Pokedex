package co.nisum.basicpokedex.presentation.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import co.nisum.basicpokedex.base.BaseAdapter
import co.nisum.basicpokedex.base.BaseViewHolder
import co.nisum.basicpokedex.data.remote.responses.Abilities
import co.nisum.basicpokedex.databinding.ItemMovesAbilitiesPokemonBinding
import co.nisum.basicpokedex.presentation.PokedexViewModel
import co.nisum.basicpokedex.presentation.models.AbilityPresentation
import co.nisum.basicpokedex.presentation.models.equals
import co.nisum.basicpokedex.utils.toCapitalize
import kotlin.math.absoluteValue


class PokemonAbilitiesAdapter:
    BaseAdapter<Abilities, PokemonAbilitiesAdapter.PokemonViewHolder>(
        diffCallBack
    ) {



    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.layout(0, 0, 0, 0)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokemonViewHolder(
            ItemMovesAbilitiesPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    inner class PokemonViewHolder(
        private val binding: ItemMovesAbilitiesPokemonBinding
    ) : BaseViewHolder<Abilities>(binding) {
        override fun bind(data: Abilities) {
            binding.apply {

                val name = "${adapterPosition+1}. ${data.ability.name.toCapitalize().replace("-"," ")}"
                textPokemonMoveAbilities.text = name
            }
        }
    }


    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Abilities>() {
            override fun areItemsTheSame(
                oldItem: Abilities,
                newItem: Abilities
            ) = equals(oldItem,newItem)

            override fun areContentsTheSame(
                oldItem: Abilities,
                newItem: Abilities
            ) = oldItem == newItem
        }
    }


}
