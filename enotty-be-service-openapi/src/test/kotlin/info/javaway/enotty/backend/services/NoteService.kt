package info.javaway.enotty.backend.services

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.models.IError
import info.javaway.enotty.backend.transport.mapping.kmp.*

class NoteService {

    suspend fun createNote(context: EnottyContext, request: CreateNoteRequest): CreateNoteResponse {
        context.setQuery(request)
        context.responseNote = Note.getModel()
        return context.toCreateResponse()
    }

    suspend fun readNote(context: EnottyContext, request: ReadNoteRequest): ReadNoteResponse{
        context.setQuery(request)
        context.responseNote = Note.getModel()
        return context.toReadResponse()
    }

    suspend fun updateNote(context: EnottyContext, request: UpdateNoteRequest): UpdateNoteResponse{
        context.setQuery(request)
        context.responseNote = Note.getModel()
        return context.toUpdateResponse()
    }

    suspend fun deleteNote(context: EnottyContext, request: DeleteNoteRequest): DeleteNoteResponse {
        context.setQuery(request)
        context.responseNote = Note.getModel()
        return context.toDeleteResponse()
    }

    suspend fun searchNote(context: EnottyContext, request: SearchNoteRequest): SearchNoteResponse {
        context.setQuery(request)
        context.responseNotes = Note.getModels().toMutableList()
        return context.toSearchResponse()
    }

    fun errorNote(context: EnottyContext, e: Throwable): BaseMessage{
        context.addError {
            from(e)
        }
        return context.toReadResponse()
    }

    fun initNote(context: EnottyContext, request: InitNoteRequest): InitNoteResponse{
        context.setQuery(request)
        return context.toInitResponse()
    }

}