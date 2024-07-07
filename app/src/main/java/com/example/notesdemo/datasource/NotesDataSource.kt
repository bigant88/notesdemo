package com.example.notesdemo.datasource

import com.example.notesdemo.domainmodels.Note

interface NotesDataSource {
    suspend fun createNote(note: Note)
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNode(note: Note): Int
    suspend fun updateNode(note: Note): Int
}