package co.nisum.basicpokedex.base

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import co.nisum.basicpokedex.presentation.pokemon_list.adapter.PokemonListAdapter

abstract class BaseAdapter<T, VH: BaseViewHolder<T>>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }


}