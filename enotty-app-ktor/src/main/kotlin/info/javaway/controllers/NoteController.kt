package info.javaway.controllers

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.EnottyContext
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import info.javaway.enotty.backend.services.NoteService
import java.time.Instant

suspend fun ApplicationCall.initNote(noteService: NoteService){
    val request = receive<InitNoteRequest>()
    val context = EnottyContext(
            startTime = Instant.now()
    )
    val result = try {
        noteService.initNote(context, request)
    } catch (e: Throwable){
        noteService.errorNote(context, e) as InitNoteResponse
    }
    respond(result)
}

suspend fun ApplicationCall.createNote(noteService: NoteService) {
    val request = receive<CreateNoteRequest>()
    val context = EnottyContext(
            startTime = Instant.now()
    )
    val result = try {
        noteService.createNote(context, request)
    } catch (e: Throwable){
        noteService.errorNote(context, e) as CreateNoteResponse
    }
    respond(result)
}


suspend fun ApplicationCall.readNote(noteService: NoteService) {
    val request = receive<ReadNoteRequest>()
    val context = EnottyContext(
            startTime = Instant.now()
    )
    val result = try {
        noteService.readNote(context, request)
    } catch (e: Throwable){
        noteService.errorNote(context, e) as ReadNoteResponse
    }
    respond(result)
}


suspend fun ApplicationCall.updateNote(noteService: NoteService) {
    val request = receive<UpdateNoteRequest>()
    val context = EnottyContext(
            startTime = Instant.now()
    )
    val result = try {
        noteService.updateNote(context, request)
    } catch (e: Throwable){
        noteService.errorNote(context, e) as UpdateNoteResponse
    }
    respond(result)
}


suspend fun ApplicationCall.deleteNote(noteService: NoteService) {
    val request = receive<DeleteNoteRequest>()
    val context = EnottyContext(
            startTime = Instant.now()
    )
    val result = try {
        noteService.deleteNote(context, request)
    } catch (e: Throwable){
        noteService.errorNote(context, e) as DeleteNoteResponse
    }
    respond(result)
}


suspend fun ApplicationCall.searchNote(noteService: NoteService) {
    val request = receive<SearchNoteRequest>()
    val context = EnottyContext(
            startTime = Instant.now()
    )
    val result = try {
        noteService.searchNote(context, request)
    } catch (e: Throwable){
        noteService.errorNote(context, e) as SearchNoteResponse
    }
    respond(result)
}