package com.example.notesdemo.repository

import android.app.Application
import com.example.notesdemo.database.getDatabase
import com.example.notesdemo.datasource.LocalDataSource
import com.example.notesdemo.datasource.NotesDataSource
import com.example.notesdemo.domainmodels.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class NotesRepository private constructor(application: Application) {


    private val localDataSource: NotesDataSource
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        val database = getDatabase(application)
        localDataSource = LocalDataSource(database.noteDao)
    }

    suspend fun createNote(note: Note) {
        localDataSource.createNote(note)
    }

    suspend fun getAllNotes(): List<Note> =
        localDataSource.getAllNotes()

    suspend fun deleteNode(note: Note) {
        localDataSource.deleteNode(note)
    }

    suspend fun updateNode(note: Note) {
        val result  = localDataSource.updateNode(note)
        println("update Node $result $note")
    }


    companion object {
        @Volatile
        private var INSTANCE: NotesRepository? = null

        fun getRepository(app: Application): NotesRepository {
            return INSTANCE ?: synchronized(this) {
                NotesRepository(app).also {
                    INSTANCE = it
                }
            }
        }
    }



}
