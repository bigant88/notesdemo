package com.example.notesdemo.domainmodels

import com.example.notesdemo.database.NoteEntity
import java.io.Serializable
import java.util.UUID

data class Note (
    var title: String ,
    var content: String ,
    var id: String = UUID.randomUUID().toString()
): Serializable

fun Note.toEntity(): NoteEntity =
    NoteEntity(title = this.title,
        content = this.content,
        id = this.id)

fun NoteEntity.toNoteModel(): Note =
    Note(title = this.title,
        content = this.content,
        id = this.id)

fun List<NoteEntity>.toListModels(): List<Note> {
    return map { it.toNoteModel() }
}
