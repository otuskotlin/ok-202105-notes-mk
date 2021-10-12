package info.javaway.enotty.backend.services

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.logic.chains.NoteCrud
import info.javaway.enotty.backend.transport.mapping.kmp.*

class NoteService(
        private val crud: NoteCrud
) {

    suspend fun createNote(context: EnottyContext, request: CreateNoteRequest): CreateNoteResponse {
        crud.create(context.setQuery(request))
        return context.toCreateResponse()
    }

    suspend fun readNote(context: EnottyContext, request: ReadNoteRequest): ReadNoteResponse{
        crud.read(context.setQuery(request))
        return context.toReadResponse()
    }

    suspend fun updateNote(context: EnottyContext, request: UpdateNoteRequest): UpdateNoteResponse{
        crud.update(context.setQuery(request))
        return context.toUpdateResponse()
    }

    suspend fun deleteNote(context: EnottyContext, request: DeleteNoteRequest): DeleteNoteResponse {
        crud.delete(context.setQuery(request))
        return context.toDeleteResponse()
    }

    suspend fun searchNote(context: EnottyContext, request: SearchNoteRequest): SearchNoteResponse {
        crud.search(context.setQuery(request))
        return context.toSearchResponse()
    }

    fun errorNote(context: EnottyContext, e: Throwable): BaseMessage{
        context.addError(e)
        return context.toReadResponse()
    }

    fun initNote(context: EnottyContext, request: InitNoteRequest): InitNoteResponse{
        context.setQuery(request)
        return context.toInitResponse()
    }

}