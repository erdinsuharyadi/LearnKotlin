package com.erdin.myroom.room

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ActivityWordAddBinding
import kotlinx.coroutines.*

class WordAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordAddBinding
    private lateinit var coroutineScope: CoroutineScope

    companion object {
        const val ADD_WORD_REQUEST_CODE = 9013
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_word_add)
        coroutineScope = CoroutineScope(Job() +  Dispatchers.IO)

        val wordDao = WordRoomDatabase.getWordDatabase(this).wordDao()

        binding.btnSave.setOnClickListener {
            if (binding.etInputWord.text.isNotEmpty()) {
                coroutineScope.launch {
                    wordDao.insert(WordRoomEntity(binding.etInputWord.text.toString(), System.currentTimeMillis()))
                }
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}