package com.erdin.myroom.marvel

interface CharacterContract {

    interface View {
        fun addListChar(list: List<CharacterEntity>)
        fun progressBarChar(view: Int)
    }

    interface Presenter {
        fun showCharList()
        fun marvelApiCoroutine()
    }
}