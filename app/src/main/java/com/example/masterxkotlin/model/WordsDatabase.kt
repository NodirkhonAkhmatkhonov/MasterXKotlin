package com.example.masterxkotlin.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordsEntity::class], version = 1)
abstract class WordsDatabase: RoomDatabase() {
    abstract fun wordsDao(): WordsDAO

    companion object {
        @Volatile private var instance: WordsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            WordsDatabase::class.java, "words.db")
            .build()
    }
}