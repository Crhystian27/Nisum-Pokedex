package co.nisum.basicpokedex.presentation.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import co.nisum.basicpokedex.base.BaseAdapter
import co.nisum.basicpokedex.base.BaseViewHolder
import co.nisum.basicpokedex.data.remote.responses.Moves
import co.nisum.basicpokedex.databinding.ItemMovesAbilitiesPokemonBinding
import co.nisum.basicpokedex.presentation.models.equals
import co.nisum.basicpokedex.utils.toCapitalize


class PokemonMoveAdapter:
    BaseAdapter<Moves, PokemonMoveAdapter.PokemonViewHolder>(
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
    ) : BaseViewHolder<Moves>(binding) {
        override fun bind(data: Moves) {
            binding.apply {

                val name = "${adapterPosition+1}. ${data.move.name.toCapitalize().replace("-"," ")}"
                textPokemonMoveAbilities.text = name

            }
        }
    }


    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Moves>() {
            override fun areItemsTheSame(
                oldItem: Moves,
                newItem: Moves
            ) = equals(oldItem,newItem)

            override fun areContentsTheSame(
                oldItem: Moves,
                newItem: Moves
            ) = oldItem == newItem
        }
    }


}
