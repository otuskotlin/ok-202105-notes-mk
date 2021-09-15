package info.javaway.services

import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.context.addError
import info.javaway.enotty.backend.common.models.IError
import info.javaway.enotty.backend.common.models.NoteIdModel

class NoteService {
    fun createNote(eContext: EnottyContext): EnottyContext {
        return eContext.apply {
            responseNote = this.requestNote.copy(id = NoteIdModel("created_note_3434"))
        }
    }

    fun readNote(eContext: EnottyContext): EnottyContext {
        val requestedId = eContext.requestNoteId.asString()
        val shouldReturnStub = Note.isCorrectedId(requestedId)

        return if (shouldReturnStub) {
            eContext.apply {
                responseNote = Note.getModel()
            }
        } else {
            eContext.addError {
                field = "requestedNoteId"
                message = "Note found note by id $requestedId"
            }
        }
    }

    fun updateNote(context: EnottyContext) = context.apply {
        responseNote = requestNote
    }

    fun deleteNote(context: EnottyContext): EnottyContext {
        val shouldReturnStub = Note.isCorrectedId(context.requestNoteId.asString())

        return if (shouldReturnStub) {
            context.apply { responseNote = Note.getModel() }
        } else {
            context.addError {
                field = "id"
                level = IError.Level.WARNING
                message = "Note with id ${context.requestNote.id.asString()} doesn't exist"
            }
        }
    }

    fun findNote(context: EnottyContext): EnottyContext {
        val requestPage = context.requestPage

        val idToFind = requestPage.lastId

        val shouldReturnStub = Note.isCorrectedId(idToFind.asString())

        return if (shouldReturnStub) {
            context.apply {
                responseNotes.addAll(Note.getModels())
            }
        } else {
            context.addError {
                field = "id"
                level = IError.Level.WARNING
                message = "Note with id ${idToFind.asString()} doesn't exist"
            }
        }
    }
}