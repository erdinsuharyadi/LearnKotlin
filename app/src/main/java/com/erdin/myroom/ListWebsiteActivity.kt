package com.erdin.myroom

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.adapter.ListWebsiteAdapter
import com.erdin.myroom.databinding.ActivityListWebsiteBinding
import com.erdin.myroom.model.Website

class ListWebsiteActivity: AppCompatActivity() {
    private lateinit var binding: ActivityListWebsiteBinding
    private lateinit var defaultSharedPref: SharedPreferences

    companion object {
        const val KEY_URL = "key_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_website)

        binding.recyclerView.adapter = ListWebsiteAdapter { partItem: Website -> partItemClicked(partItem)}
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        defaultSharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }

    private fun partItemClicked(partItem : Website) {
        Toast.makeText(this, "Clicked: ${partItem.url}", Toast.LENGTH_LONG).show()
        defaultSharedPref.edit().putString(KEY_URL, partItem.url).apply()

    }
}