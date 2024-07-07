package com.example.notesdemo.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesdemo.domainmodels.Note
import com.example.notesdemo.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListNotesViewModel(app: Application): AndroidViewModel(app)  {
    private val repository = NotesRepository.getRepository(app)
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes
    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun getAllNotes() {
        _isRefreshing.postValue(true)
        uiScope.launch {
            try {
                val allNotes = repository.getAllNotes()
                _isRefreshing.postValue(false)
                _notes.postValue(allNotes)
            }catch (e: Exception){
                _isRefreshing.postValue(false)
            }
        }
    }
}