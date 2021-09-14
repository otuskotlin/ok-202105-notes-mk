package info.javaway.enotty.backend.common.context

import info.javaway.enotty.backend.common.models.*

data class EnottyContext(
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

fun EnottyContext.addError(lambda: CommonErrorModel.() -> Unit) =
        apply {
            status = CorStatus.FAILING
            errors.add(
                    CommonErrorModel(
                            field = "_", level = IError.Level.ERROR
                    ).apply(lambda)
            )
        }
