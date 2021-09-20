package info.javaway

import info.javaway.controllers.*
import info.javaway.services.NoteService
import io.ktor.application.*
import io.ktor.routing.*

fun Routing.note(noteService: NoteService) = route("note"){
    post("create") { call.createNote(noteService) }
    post("read"){call.readNote(noteService)}
    post("update"){call.updateNote(noteService)}
    post("delete"){call.deleteNote(noteService)}
    post("search"){call.searchNote(noteService)}
}