package com.erdin.myroom.room

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ActivityShowWebBinding
import com.erdin.myroom.databinding.ActivityWordListBinding
import com.erdin.myroom.room.WordAddActivity.Companion.ADD_WORD_REQUEST_CODE
import kotlinx.coroutines.*

class WordListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWordListBinding
    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_word_list)
        coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

        binding.rvWords.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvWords.adapter = WordListAdapter()

        binding.btnAddWord.setOnClickListener {
            val intent = Intent(this, WordAddActivity::class.java)
            startActivity(intent)
        }

        populateList()
    }

    private fun populateList() {
        coroutineScope.launch {
            val wordDao = WordRoomDatabase.getWordDatabase(this@WordListActivity).wordDao()
            val list = wordDao.getAllWord()
            withContext(Dispatchers.Main) {
                (binding.rvWords.adapter as WordListAdapter).addItems(list)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == ADD_WORD_REQUEST_CODE) {
            populateList()
        }
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }


}
