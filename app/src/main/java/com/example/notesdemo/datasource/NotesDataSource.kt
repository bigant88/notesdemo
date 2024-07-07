package com.example.notesdemo.datasource

import com.example.notesdemo.domainmodels.Note

interface NotesDataSource {
    suspend fun saveNote(note: Note)
    suspend fun getAllNotes(): List<Note>
}