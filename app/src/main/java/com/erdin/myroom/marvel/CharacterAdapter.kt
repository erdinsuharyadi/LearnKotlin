package com.erdin.myroom.marvel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ItemRvCharacterBinding

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
        holder.binding.tvAge.text = item.description
        holder.binding.tvName.text = item.name
//        holder.binding.tvSalary.text = item.description
    }

    class CharacterHolder(val binding: ItemRvCharacterBinding) : RecyclerView.ViewHolder(binding.root)
}