package com.erdin.myroom.marvel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.MainActivity
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ItemRvCharacterBinding
import com.erdin.myroom.databinding.ItemRvHeroBinding
import com.erdin.myroom.room.WordListActivity
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    private val items = mutableListOf<CharacterEntity>()

    fun addList(list: List<CharacterEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_rv_hero, parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val item =items[position]
        holder.binding.tvHeroName.text = item.name

        val mContext = holder.binding.cvHero.context

        Picasso.get()
            .load(item.imageCharacter)
            .placeholder(R.drawable.ic_image_white_24dp)
            .error(R.drawable.ic_broken_image_white_24dp)
            .into(holder.binding.ivImgHero)

        holder.binding.cvHero.setOnClickListener {
            val intent = Intent(mContext, CharacterDetailActivity::class.java)
            intent.putExtra("hero_name", item.name)
            intent.putExtra("hero_desc", item.description)
            intent.putExtra("hero_image", item.imageCharacter)
            intent.putExtra("hero_detail", item.urlDetail)
            mContext.startActivity(intent)
        }
    }

    class CharacterHolder(val binding: ItemRvHeroBinding) : RecyclerView.ViewHolder(binding.root)
}