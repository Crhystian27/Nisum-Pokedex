package co.nisum.basicpokedex.presentation.pokemon.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import co.nisum.basicpokedex.BuildConfig
import co.nisum.basicpokedex.base.BaseAdapter
import co.nisum.basicpokedex.base.BaseViewHolder
import co.nisum.basicpokedex.data.remote.responses.Species
import co.nisum.basicpokedex.databinding.ItemPokemonBinding
import co.nisum.basicpokedex.presentation.models.equals
import co.nisum.basicpokedex.utils.extractNumberFromUrl
import co.nisum.basicpokedex.utils.loadImage
import co.nisum.basicpokedex.utils.toCapitalize

class PokemonEvolutionAdapter:
BaseAdapter<Species,PokemonEvolutionAdapter.PokemonViewHolder>(
    diffCallBack
){

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
    ) : BaseViewHolder<Species>(binding) {
        override fun bind(data: Species) {
            binding.apply {

                val number = data.url.extractNumberFromUrl()

                titlePokemon.text = data.name.toCapitalize()

                imgPokemon.loadImage(
                    progressBar = progressPokemon,
                    view = cardPokemon,
                    orientation = GradientDrawable.Orientation.BOTTOM_TOP,
                    imageUrl = BuildConfig.BASE_URL_IMG + "/${number}.png")
            }
        }
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Species>() {
            override fun areItemsTheSame(
                oldItem: Species,
                newItem: Species
            ) = equals(oldItem, newItem)

            override fun areContentsTheSame(
                oldItem: Species,
                newItem: Species
            ) = oldItem == newItem
        }
    }



}