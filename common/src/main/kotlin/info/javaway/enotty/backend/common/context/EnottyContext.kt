package info.javaway.enotty.backend.common.context

import info.javaway.enotty.backend.common.models.*
import info.javaway.enotty.backend.common.models.PaginatedModel
import java.time.Instant

data class EnottyContext(
        var startTime: Instant = Instant.MIN,
        var operation: EnottyOperations = EnottyOperations.NONE,

        var onRequest: String = "",
        var requestNoteId: NoteIdModel = NoteIdModel.NONE,
        var requestNote: NoteModel = NoteModel(),
        var responseNote: NoteModel = NoteModel(),
        var requestPage: PaginatedModel = PaginatedModel(),
        var responsePage: PaginatedModel = PaginatedModel(),
        var responseNotes: MutableList<NoteModel> = mutableListOf(),
        var errors: MutableList<IError> = mutableListOf(),
        var status: CorStatus = CorStatus.STARTED,
){
    enum class EnottyOperations{
        NONE,
        INIT,
        CREATE,
        READ,
        UPDATE,
        DELETE,
        SEARCH
    }

    /**
     * Добавляет ошибку в контекст
     *
     * @param failingStatus Необходимо ли установить статус выполнения в FAILING (true/false)
     * @param
     */
    fun addError(failingStatus: Boolean = true, lambda: CommonErrorModel.() -> Unit) = apply {
        if (failingStatus) status = CorStatus.FAILING
        errors.add(
                CommonErrorModel(
                        field = "_", level = IError.Level.ERROR
                ).apply(lambda)
        )
    }
}