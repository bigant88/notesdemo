package com.example.notesdemo.ui.addedit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesdemo.domainmodels.Note
import com.example.notesdemo.repository.NotesRepository
import kotlinx.coroutines.launch

class AddEditNoteViewModel(app: Application): AndroidViewModel(app) {
    private val repository = NotesRepository.getRepository(app)
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    fun saveNote(title: String, content: String){
        println("saveNote begin")
        val note = Note(title = title , content =  content)
        createNote(note)
    }

    private fun createNote(note: Note) = viewModelScope.launch {
        repository.createNote(note)
    }
}