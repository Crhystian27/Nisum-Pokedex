package co.nisum.basicpokedex.presentation.pokemon_list.adapter


import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import co.nisum.basicpokedex.BuildConfig

import co.nisum.basicpokedex.base.BaseAdapter
import co.nisum.basicpokedex.base.BaseViewHolder
import co.nisum.basicpokedex.databinding.ItemPokemonBinding
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.models.equals
import co.nisum.basicpokedex.utils.loadImage
import co.nisum.basicpokedex.utils.toCapitalize


class PokemonListAdapter(private val listener: PokemonClick) :
    BaseAdapter<PokemonListPresentation, PokemonListAdapter.PokemonViewHolder>(
        diffCallBack
    ) {


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.layout(0, 0, 0, 0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokemonViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    inner class PokemonViewHolder(
        private val binding: ItemPokemonBinding
    ) : BaseViewHolder<PokemonListPresentation>(binding) {
        override fun bind(data: PokemonListPresentation) {
            binding.apply {

                val url = data.url.dropLast(1)
                val number = url.substring(url.lastIndexOf('/')).replace("/", "")

                titlePokemon.text = data.name.toCapitalize()

                imgPokemon.loadImage(
                    progressBar = progressPokemon,
                    view = cardPokemon,
                    orientation = GradientDrawable.Orientation.BOTTOM_TOP,
                    imageUrl = BuildConfig.BASE_URL_IMG + "/${number}.png")


                imgPokemon.setOnClickListener {
                    listener.onClick(number)
                }
            }
        }
    }


    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<PokemonListPresentation>() {
            override fun areItemsTheSame(
                oldItem: PokemonListPresentation,
                newItem: PokemonListPresentation
            ) = equals(oldItem, newItem)

            override fun areContentsTheSame(
                oldItem: PokemonListPresentation,
                newItem: PokemonListPresentation
            ) = oldItem == newItem
        }
    }


}


interface PokemonClick {
    fun onClick(number: String)
}