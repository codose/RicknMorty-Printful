package com.ogie.printfultest.views.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ogie.printfultest.R
import com.ogie.printfultest.databinding.ItemListItemBinding
import com.ogie.printfultest.model.RickMorty

class ListRecyclerAdapter(private val context: Context, private val clickListener: ListClickListener) : ListAdapter<RickMorty, ListRecyclerAdapter.ListViewHolder>(ListDiffUtilCallback()) {
    inner class ListViewHolder(val binding : ItemListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            item: RickMorty,
            context: Context,
            clickListener: ListClickListener,
        ){
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
            binding.location.text = item.location.name
            binding.name.text = item.name
            Glide.with(context)
                .load(item.image)
                .placeholder(R.drawable.placeholder)
                .into(binding.image)
            binding.type.text = "${item.status} - ${item.species}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListItemBinding.inflate(layoutInflater, parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position), context, clickListener)
    }
}

class ListClickListener(val clickListener: (item : RickMorty) -> Unit){
    fun onClick(item: RickMorty) = clickListener(item)
}

class ListDiffUtilCallback : DiffUtil.ItemCallback<RickMorty>(){
    override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
        return oldItem == newItem
    }
}