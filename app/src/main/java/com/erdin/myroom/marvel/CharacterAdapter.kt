package com.erdin.myroom.marvel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ItemRvCharacterBinding
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    private val items = mutableListOf<CharacterEntity>()

    fun addList(list: List<CharacterEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_rv_character, parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val item =items[position]
        holder.binding.tvDesc.text = item.description
        holder.binding.tvName.text = item.name

        Picasso.get()
            .load(item.imageCharacter)
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(holder.binding.ivCharacter)
    }

    class CharacterHolder(val binding: ItemRvCharacterBinding) : RecyclerView.ViewHolder(binding.root)
}