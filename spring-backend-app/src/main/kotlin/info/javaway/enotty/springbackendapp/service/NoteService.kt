package info.javaway.enotty.springbackendapp.service

import Note
import info.javaway.enotty.backend.common.context.MpContext
import info.javaway.enotty.backend.common.models.IError
import info.javaway.enotty.springbackendapp.addError
import org.springframework.stereotype.Service

@Service
class NoteService {

    fun createNote(mpContext: MpContext): MpContext {
        return mpContext.apply {
            responseNote = Note.getModel()
        }
    }

    fun readNote(mpContext: MpContext): MpContext {
        val requestId = mpContext.requestNoteId.id
        val shouldReturnStub = Note.isCorrectedId(requestId)
        println("haha $shouldReturnStub")
        return if (shouldReturnStub)
            mpContext.apply {
                responseNote = Note.getModel()
            } else {
            mpContext.addError {
                field = "requestedNoteId"
                message = "Not found note by id: $requestId"
            }
        }
    }

    fun updateNote(context: MpContext) = context.apply {
        responseNote = requestNote
    }

    fun deleteNote(context: MpContext): MpContext {
        val shouldReturnStub = Note.isCorrectedId(context.requestNoteId.id)
        return if (shouldReturnStub) {
            context.apply {
                responseNote = requestNote
            }
        } else {
            context.addError {
                field = "id"
                level = IError.Level.WARNING
                message = "Note with id ${context.requestNote.id.id} doesn't exist"
            }
        }
    }

    fun findNote(context: MpContext): MpContext{
        val requestPage = context.requestPage

        val idToFind = requestPage.lastId

        val shouldReturnStub = Note.isCorrectedId(idToFind.id)

        return if(shouldReturnStub){
            context.apply {
                responseNotes.addAll(Note.getModels())
            }
        } else {
            context.addError {
                field = "id"
                level = IError.Level.WARNING
                message = "Note with id ${idToFind.id} doesn't exist"
            }
        }
    }
}