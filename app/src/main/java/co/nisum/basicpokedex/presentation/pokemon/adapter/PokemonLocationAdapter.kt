package co.nisum.basicpokedex.presentation.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import co.nisum.basicpokedex.base.BaseAdapter
import co.nisum.basicpokedex.base.BaseViewHolder
import co.nisum.basicpokedex.databinding.ItemMovesAbilitiesPokemonBinding
import co.nisum.basicpokedex.presentation.models.LocationPresentation
import co.nisum.basicpokedex.presentation.models.equals
import co.nisum.basicpokedex.utils.toCapitalize

class PokemonLocationAdapter :
    BaseAdapter<LocationPresentation, PokemonLocationAdapter.LocationViewHolder>(
    diffCallBack
){

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.layout(0, 0, 0, 0)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocationViewHolder(
            ItemMovesAbilitiesPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    inner class LocationViewHolder(
        private val binding: ItemMovesAbilitiesPokemonBinding
    ) : BaseViewHolder<LocationPresentation>(binding) {
        override fun bind(data: LocationPresentation) {
            binding.apply {


                val name = "${adapterPosition+1}. ${data.location_area.name.toCapitalize().replace("-"," ")}"
                textPokemonMoveAbilities.text = name

            }
        }
    }


    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<LocationPresentation>() {
            override fun areItemsTheSame(
                oldItem: LocationPresentation,
                newItem: LocationPresentation
            ) = equals(oldItem,newItem)

            override fun areContentsTheSame(
                oldItem: LocationPresentation,
                newItem: LocationPresentation
            ) = oldItem == newItem
        }
    }

}