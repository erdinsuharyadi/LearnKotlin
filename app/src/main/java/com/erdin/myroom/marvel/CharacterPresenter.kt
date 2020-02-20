package com.erdin.myroom.marvel

import android.util.Log
import android.view.View
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterPresenter(private val coroutineScope: CoroutineScope,
                         private val charDao: CharacterDao,
                         private val view: CharacterContract.View) : CharacterContract.Presenter {


    override fun showCharList() {
        coroutineScope.launch {
            val listChar = charDao.getAllChar()
            withContext(Dispatchers.Main) {

                view.progressBarChar(View.VISIBLE)

                if (listChar.isEmpty()) {
                    Log.d("RoomDB", "Kosong")

                    marvelApiCoroutine()
                } else {
                    Log.d("RoomDB", "Ada isinya")
                    Log.d("RoomDB", listChar.toString())

                    view.progressBarChar(View.GONE)
                    view.addListChar(listChar)
                }
            }
        }
    }

    override fun marvelApiCoroutine() {
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

                showCharList()

            } else if (response is Throwable) {
                Log.d("errorApi", response.message ?: "Error")
            }
        }
    }
}