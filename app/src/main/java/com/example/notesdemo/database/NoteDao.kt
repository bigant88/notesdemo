package com.example.notesdemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("select * from notes")
    fun getNotes(): List<NoteEntity>

    /**
     * Insert a note in the database. If the note already exists, replace it.
     *
     * @param note the note to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity): Int
    /**
     * Delete a note by id.
     *
     * @return the number of notes deleted. This should always be 1.
     */
    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun delete(noteId: String): Int


}