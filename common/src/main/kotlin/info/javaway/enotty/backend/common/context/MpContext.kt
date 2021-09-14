package info.javaway.enotty.backend.common.context

import info.javaway.enotty.backend.common.models.IError
import info.javaway.enotty.backend.common.models.NoteIdModel
import info.javaway.enotty.backend.common.models.NoteModel
import info.javaway.enotty.backend.common.models.PaginatedModel

data class MpContext(
        var onRequest: String = "",
        var requestNoteId: NoteIdModel = NoteIdModel.NONE,
        var requestNote: NoteModel = NoteModel(),
        var responseNote: NoteModel = NoteModel(),
        var requestPage: PaginatedModel = PaginatedModel(),
        var responsePage: PaginatedModel = PaginatedModel(),
        var responseNotes: MutableList<NoteModel> = mutableListOf(),
        var errors: MutableList<IError> = mutableListOf(),
        var status: CorStatus = CorStatus.STARTED,
)
