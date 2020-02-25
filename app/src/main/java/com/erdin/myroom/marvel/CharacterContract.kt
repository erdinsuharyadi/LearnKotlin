package com.erdin.myroom.marvel

interface CharacterContract {

    interface View {
        fun addListChar(list: List<CharacterEntity>)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter {
        fun showCharList()
        fun marvelApiCoroutine()
    }
}