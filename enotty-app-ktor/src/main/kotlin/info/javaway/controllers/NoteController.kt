package info.javaway.controllers

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.transport.mapping.kmp.*
import info.javaway.services.NoteService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

suspend fun ApplicationCall.createNote(noteService: NoteService) {
    val createNoteRequest = receive<CreateNoteRequest>()
    val eContext = EnottyContext().setQuery(createNoteRequest)
    val eContextResponse = noteService.createNote(eContext)
    val eContextReturned = eContextResponse.toCreateResponse()
    respond(eContextReturned)
//    respond(
//           EnottyContext().setQuery(createNoteRequest).let {
//               noteService.createNote(it)
//           }.toCreateResponse()
//    )
}


suspend fun ApplicationCall.readNote(noteService: NoteService) {
    val readNoteRequest = receive<ReadNoteRequest>()
    respond(
            EnottyContext().setQuery(readNoteRequest).let {
                noteService.readNote(it)
            }.toReadResponse()
    )
}


suspend fun ApplicationCall.updateNote(noteService: NoteService) {
    val updateNoteRequest = receive<UpdateNoteRequest>()
    println(updateNoteRequest)
    respond(
            EnottyContext().setQuery(updateNoteRequest).let { context ->
                println(context)
                noteService.updateNote(context)
            }.toUpdateResponse()
    )
}


suspend fun ApplicationCall.deleteNote(noteService: NoteService) {
    val deleteNoteRequest = receive<DeleteNoteRequest>()
    respond(
            EnottyContext().setQuery(deleteNoteRequest).let {
                val x = noteService.deleteNote(it)
                x
            }.toDeleteResponse()
    )
}


suspend fun ApplicationCall.searchNote(noteService: NoteService) {
    val searchNoteRequest = receive<SearchNoteRequest>()
    respond(
            EnottyContext().setQuery(searchNoteRequest).let {
                noteService.findNote(it)
            }.toSearchResponse()
    )
}