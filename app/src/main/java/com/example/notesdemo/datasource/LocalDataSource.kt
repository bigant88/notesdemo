package com.example.notesdemo.datasource

import com.example.notesdemo.database.NoteDao
import com.example.notesdemo.domainmodels.Note
import com.example.notesdemo.domainmodels.toEntity
import com.example.notesdemo.domainmodels.toListModels
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(
    private val noteDao: NoteDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : NotesDataSource {

    override suspend fun createNote(note: Note) = withContext(ioDispatcher){
        noteDao.insertNote(note.toEntity())
    }

    override suspend fun getAllNotes(): List<Note> = withContext(ioDispatcher) {
        return@withContext try {
            noteDao.getNotes().toListModels()
        } catch (e: Exception) {
            emptyList<Note>()
        }
    }

    override suspend fun deleteNode(note: Note): Int = withContext(ioDispatcher){
         return@withContext noteDao.delete(note.id)
    }

    override suspend fun updateNode(note: Note): Int = withContext(ioDispatcher){
        return@withContext noteDao.updateNote(note.toEntity())
    }

}


