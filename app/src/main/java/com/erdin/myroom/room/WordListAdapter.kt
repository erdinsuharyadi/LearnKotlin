package com.erdin.myroom.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.R
import java.util.*

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    private val items = mutableListOf<WordRoomEntity>()

    fun addItems(words: List<WordRoomEntity>) {
        items.clear()
        items.addAll(words)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_word, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = items[position]
        holder.tvWordNo.text = word.id.toString()
        holder.tvWordName.text = word.name
        holder.tvCreatedDate.text = formatDate(word.createdAt)
    }

    private fun formatDate(date: Long): String {
        val formattedDate: String
        val c = Calendar.getInstance()
        c.timeInMillis = date
        formattedDate = "${c.get(Calendar.DAY_OF_MONTH)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.YEAR)} ${c.get(Calendar.HOUR_OF_DAY)}:${c.get(Calendar.MINUTE)}"

        return formattedDate
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWordNo = itemView.findViewById<TextView>(R.id.tv_word_no)
        val tvWordName = itemView.findViewById<TextView>(R.id.tv_word_name)
        val tvCreatedDate = itemView.findViewById<TextView>(R.id.tv_created_date)
    }
}