package co.nisum.basicpokedex.presentation.pokemon_list.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import co.nisum.basicpokedex.BuildConfig
import co.nisum.basicpokedex.R

import co.nisum.basicpokedex.base.BaseAdapter
import co.nisum.basicpokedex.base.BaseViewHolder
import co.nisum.basicpokedex.databinding.ItemPokemonBinding
import co.nisum.basicpokedex.presentation.models.PokemonListPresentation
import co.nisum.basicpokedex.presentation.models.equals
import coil.load
import java.util.*


class PokemonListAdapter(private val listener: PokemonClick) :
    BaseAdapter<PokemonListPresentation, PokemonListAdapter.BirdViewHolder>(
        diffCallBack
    ) {


    override fun onBindViewHolder(holder: BirdViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.layout(0, 0, 0, 0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BirdViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    inner class BirdViewHolder(
        private val binding: ItemPokemonBinding
    ) : BaseViewHolder<PokemonListPresentation>(binding) {
        override fun bind(data: PokemonListPresentation) {
            binding.apply {


                val url = data.imageUrl.dropLast(1)
                val number = url.substring(url.lastIndexOf('/')).replace("/", "")

                titlePokemon.text = data.pokemonName.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }

                imgPokemon.load(BuildConfig.BASE_URL_IMG + "/${number}.png") {
                    placeholder(R.drawable.ic_launcher_foreground)
                    progressPokemon.visibility = View.GONE

                        /*.target {
                            Palette.Builder(it.toBitmap()).generate { pa ->
                                pa?.let { palette ->
                                    cardPokemon.setBackgroundColor(
                                        palette.getDominantColor(
                                            ContextCompat.getColor(
                                                root.context,
                                                R.color.white
                                            )
                                        )
                                    )
                                }
                            }
                        }*/
                }

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