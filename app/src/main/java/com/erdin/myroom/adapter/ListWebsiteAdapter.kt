package com.erdin.myroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.R
import com.erdin.myroom.model.Website

class ListWebsiteAdapter(private val clickListener: (Website) -> Unit): RecyclerView.Adapter<ListWebsiteAdapter.ListWebViewHolder>() {

    private val listWeb = listOf(
        Website("Google", "https://google.com"),
        Website("Twitter", "https://twitter.com"),
        Website("Kotlin", "https://kotlinlang.org/"),
        Website("Arkademy", "https://www.arkademy.com/"),
        Website("Facebook", "https://www.facebook.com/"),
        Website("Instagram", "https://www.instagram.com/"),
        Website("Stackoverflow", "https://stackoverflow.com/"),
        Website("Medium", "https://medium.com/"),
        Website("Github", "https://github.com/"),
        Website("Petani Kode","https://www.petanikode.com/")

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListWebViewHolder {
        return ListWebViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_list_website, parent,false))
    }

    override fun getItemCount(): Int = listWeb.size

    override fun onBindViewHolder(holder: ListWebViewHolder, position: Int) {
        holder.bind(listWeb[position], clickListener)
    }

    class ListWebViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(part: Website, clickListener: (Website) -> Unit) {
            itemView.findViewById<TextView>(R.id.tv_web_name).text = part.title
            itemView.setOnClickListener { clickListener(part) }
        }
    }

}