package com.example.notesdemo.ui.addedit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesdemo.domainmodels.Note
import com.example.notesdemo.repository.NotesRepository
import kotlinx.coroutines.launch

class AddEditNoteViewModel(app: Application): AndroidViewModel(app) {
    private val repository = NotesRepository.getRepository(app)
    private var editingNote: Note? = null
    private val _isUpdateCompleted = MutableLiveData<Boolean>()
    val isUpdateCompleted: LiveData<Boolean> = _isUpdateCompleted

    fun createNote(title: String, content: String){
        val note = Note(title = title , content =  content)
        createNote(note)
    }

    private fun createNote(note: Note) = viewModelScope.launch {
        repository.createNote(note)
        _isUpdateCompleted.postValue(true)
    }

    fun startEdit(note: Note) {
        editingNote = note
    }

    fun updateNote(title: String, content: String) = viewModelScope.launch {
        repository.updateNode(Note(title, content, editingNote!!.id))
        _isUpdateCompleted.postValue(true)
    }
}