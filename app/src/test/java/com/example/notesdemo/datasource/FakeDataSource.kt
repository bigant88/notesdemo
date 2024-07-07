package com.example.notesdemo.datasource

import com.example.notesdemo.domainmodels.Note

class FakeDataSource(var notes: MutableList<Note>? = mutableListOf()) : NotesDataSource {

    override suspend fun createNote(note: Note) {
        notes?.add(note)
    }

    override suspend fun getAllNotes(): List<Note> {
        notes?.let { return it }
        return emptyList()
    }

    override suspend fun deleteNode(newNote: Note): Int {
        if(notes != null){
            var index = -1
            for(note in notes!!){
                if(note.id == newNote.id){
                    index = notes!!.indexOf(note)
                }
            }
            if(index != -1){
                notes!!.removeAt(index)
                return 1
            }
        }
        return 0
    }

    override suspend fun updateNode(newNote: Note): Int {
        if(notes != null){
            var index = -1
            for(note in notes!!){
                if(note.id == newNote.id){
                    index = notes!!.indexOf(note)
                }
            }
            if(index != -1){
                notes!![index] = newNote
                return 1
            }
        }
        return 0
    }
}