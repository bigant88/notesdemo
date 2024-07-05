package com.example.notesdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
}

private lateinit var INSTANCE: NotesDatabase

fun getDatabase(context: Context): NotesDatabase {
    synchronized(NotesDatabase::class.java) {
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                NotesDatabase::class.java, "notes").build()
        }
    }
    return INSTANCE
}