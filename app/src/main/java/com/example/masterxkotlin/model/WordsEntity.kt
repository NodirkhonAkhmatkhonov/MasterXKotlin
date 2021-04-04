package com.example.masterxkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordsEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "first") var first: String,
    @ColumnInfo(name = "second") var second: String
    )