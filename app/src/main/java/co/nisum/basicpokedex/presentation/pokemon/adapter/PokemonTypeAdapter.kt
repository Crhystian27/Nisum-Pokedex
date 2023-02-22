package co.nisum.basicpokedex.presentation.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import co.nisum.basicpokedex.base.BaseAdapter
import co.nisum.basicpokedex.base.BaseViewHolder
import co.nisum.basicpokedex.data.remote.responses.Types
import co.nisum.basicpokedex.databinding.ItemTypePokemonBinding
import co.nisum.basicpokedex.presentation.models.equals
import co.nisum.basicpokedex.utils.toCapitalize
import co.nisum.basicpokedex.utils.toColorResourceId


class PokemonTypeAdapter:
    BaseAdapter<Types, PokemonTypeAdapter.PokemonViewHolder>(
        diffCallBack
    ) {


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.layout(0, 0, 0, 0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokemonViewHolder(
            ItemTypePokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    inner class PokemonViewHolder(
        private val binding: ItemTypePokemonBinding
    ) : BaseViewHolder<Types>(binding) {
        override fun bind(data: Types) {
            binding.apply {

                cardPokemonType.setCardBackgroundColor(root.context.getColor(data.type.name.toColorResourceId()))
                textPokemonType.text = data.type.name.toCapitalize()

            }
        }
    }


    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Types>() {
            override fun areItemsTheSame(
                oldItem: Types,
                newItem: Types
            ) = equals(oldItem,newItem)

            override fun areContentsTheSame(
                oldItem: Types,
                newItem: Types
            ) = oldItem == newItem
        }
    }


}
