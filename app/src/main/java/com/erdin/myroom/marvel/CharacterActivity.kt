package com.erdin.myroom.marvel

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ActivityCharacterBinding
import kotlinx.coroutines.*


class CharacterActivity : AppCompatActivity(), CharacterContract.View {

    private lateinit var binding: ActivityCharacterBinding
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var charDao: CharacterDao
    private var presenter: CharacterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        title = "Marvel Heroes"

        coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
        charDao = CharacterDatabase.getCharDatabase(this).characterDao()

        binding.rvCharacter.adapter = CharacterAdapter()
        binding.rvCharacter.layoutManager = GridLayoutManager(this,3, RecyclerView.VERTICAL, false)

        presenter = CharacterPresenter(coroutineScope,charDao,this)

        presenter?.showCharList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addListChar(list: List<CharacterEntity>) {
        (binding.rvCharacter.adapter as CharacterAdapter).addList(list)
    }

    override fun progressBarChar(view: Int) {
        binding.pbCharacter.visibility = view
    }

    override fun onDestroy() {
        presenter = null
        coroutineScope.cancel()
        super.onDestroy()
    }
}