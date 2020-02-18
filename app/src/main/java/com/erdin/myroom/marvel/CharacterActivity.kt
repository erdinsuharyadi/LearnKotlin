package com.erdin.myroom.marvel

import android.app.Activity
import android.content.Intent
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.erdin.myroom.R
import com.erdin.myroom.databinding.ActivityCharacterBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var charDao: CharacterDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character)
        coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

        binding.rvCharacter.adapter = CharacterAdapter()
        binding.rvCharacter.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        charDao = CharacterDatabase.getCharDatabase(this).characterDao()

        showCharList()

    }

    private fun showCharList() {
        coroutineScope.launch {
            val listChar = charDao.getAllChar()
            withContext(Dispatchers.Main) {
                if (listChar.isEmpty()) {
                    Log.d("RoomDB", "Kosong")
                    marvelApiCoroutine()
                } else {
                    Log.d("RoomDB", "Ada isinya")
                    Log.d("RoomDB", listChar.toString())

                    (binding.rvCharacter.adapter as CharacterAdapter).addList(listChar)
                }

            }
        }
    }

    private fun marvelApiCoroutine() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CharacterApiService::class.java)

        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

        coroutineScope.launch {

            val response = withContext(Dispatchers.IO) {
                try {
                    service.getCharacter()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }

            if(response is CharacterListResponse) {
                Log.d("marvelApiSukses", response.toString())

                withContext(Dispatchers.IO) {
                    response.data?.results?.map {
                        charDao.insert(CharacterEntity(it.id.orEmpty(), it.name.orEmpty(), it.description.orEmpty(), it.imageCharacter?.path + "/portrait_medium."+ it.imageCharacter?.extension, it.urls?.get(0)?.url.orEmpty()))
                    }
                }

                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)

            } else if (response is Throwable) {
                Log.d("errorApi", response.message ?: "Error")
            }
        }
    }



}