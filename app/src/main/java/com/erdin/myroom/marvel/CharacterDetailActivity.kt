package com.erdin.myroom.marvel

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ActivityCharacterDetailBinding
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Hero Detail"

        binding.tvDtlHeroName.text = intent.extras?.getString("hero_name")
        binding.tvDtlHeroDesc.text = intent.extras?.getString("hero_desc")
        Picasso.get().load(intent.extras?.getString("hero_image")).into(binding.ivDtlImgHero)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}