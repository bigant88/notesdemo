package com.example.notesdemo.ui.notedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesdemo.domainmodels.Note
import com.example.notesdemo.repository.NotesRepository
import kotlinx.coroutines.launch

class NoteDetailViewModel(app: Application): AndroidViewModel(app)  {
    private val repository = NotesRepository.getRepository(app)
    lateinit var note: Note
    lateinit var title: String
    lateinit var content: String
    private val _isDeleteCompleted = MutableLiveData<Boolean>()
    val isDeleteCompleted: LiveData<Boolean> = _isDeleteCompleted

    fun start(note: Note) {
        this.note = note
        this.title = note.title
        this.content = note.content
    }

    fun deleteNote() {
        viewModelScope.launch {
            repository.deleteNode(note)
            _isDeleteCompleted.postValue(true)
        }
    }

}