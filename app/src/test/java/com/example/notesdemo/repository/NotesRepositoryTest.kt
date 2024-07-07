package com.example.notesdemo.repository

import com.example.notesdemo.datasource.FakeDataSource
import com.example.notesdemo.domainmodels.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual

import org.junit.Test
@ExperimentalCoroutinesApi
class NotesRepositoryTest {
    private val note1 = Note("Title1", "Content1", "1")
    private val note2 = Note("Title2", "Content2", "2")
    private val note3 = Note("Title3", "Content3", "3")
    private val note4 = Note("Title4", "Content4", "4")
    private val newNote3 = Note("NewTitle3", "NewContent3", "3")
    private val notes = listOf(note1, note2, note3)
    private lateinit var localDataSource: FakeDataSource
    // Class under test
    private lateinit var notesRepository: NotesRepository

    @Before
    fun createRepository() {
        localDataSource = FakeDataSource(notes.toMutableList())
        notesRepository = NotesRepository(localDataSource)
    }

    @Test
    fun createNote() = runTest {
        localDataSource.createNote(note4)
        val allNotes = notesRepository.getAllNotes()
        assertThat(allNotes, IsEqual(listOf(note1, note2, note3, note4)))
    }

    @Test
    fun getAllNotes() = runTest {
        val allNotes = notesRepository.getAllNotes()
        assertThat(allNotes, IsEqual(notes))
    }

    @Test
    fun deleteNode() = runTest {
        notesRepository.deleteNode(note2)
        val allNotes = notesRepository.getAllNotes()
        assertThat(allNotes, IsEqual(listOf(note1, note3)))
    }

    @Test
    fun updateNode() = runTest {
        notesRepository.updateNode(newNote3)
        val allNotes = notesRepository.getAllNotes()
        assertThat(allNotes, IsEqual(listOf(note1, note2, newNote3)))
    }
}