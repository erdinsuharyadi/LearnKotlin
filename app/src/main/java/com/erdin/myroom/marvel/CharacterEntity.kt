package com.erdin.myroom.marvel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterEntity(@PrimaryKey var id: String,
                           var name: String,
                           var description: String,
                           var imageCharacter: String,
                           var urlDetail: String)