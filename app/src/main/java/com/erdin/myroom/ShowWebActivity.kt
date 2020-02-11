package com.erdin.myroom

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.databinding.DataBindingUtil
import com.erdin.myroom.ListWebsiteActivity.Companion.KEY_URL
import com.erdin.myroom.databinding.ActivityShowWebBinding

class ShowWebActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding : ActivityShowWebBinding

    private var urlString : String? = null
    private lateinit var defaultSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_web)

        binding.btnListWeb.setOnClickListener {
            val intent = Intent(this, ListWebsiteActivity::class.java)
            startActivity(intent)
        }

        defaultSharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        urlString = defaultSharedPref.getString(KEY_URL, ".....")
        binding.tvWeb.text = urlString
        defaultSharedPref.registerOnSharedPreferenceChangeListener(this)

        binding.btnShowWeb.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url_web", urlString.toString())
            startActivity(intent)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        urlString = defaultSharedPref.getString(KEY_URL, ".....")
        binding.tvWeb.text = urlString
    }


}

