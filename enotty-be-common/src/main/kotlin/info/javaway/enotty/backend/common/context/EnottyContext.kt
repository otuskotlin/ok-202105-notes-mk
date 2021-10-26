package info.javaway.enotty.backend.common.context

import info.javaway.enotty.backend.common.models.*
import info.javaway.enotty.backend.common.models.PaginatedModel
import java.time.Instant

data class EnottyContext(
        var startTime: Instant = Instant.MIN,
        var operation: EnottyOperations = EnottyOperations.NONE,
        var stubCase: EnottyStubCase = EnottyStubCase.NONE,

        var onRequest: String = "",
        var requestNoteId: NoteIdModel = NoteIdModel.NONE,
        var requestNote: NoteModel = NoteModel(),
        var responseNote: NoteModel = NoteModel(),
        var requestPage: PaginatedModel = PaginatedModel(),
        var responsePage: PaginatedModel = PaginatedModel(),
        var responseNotes: MutableList<NoteModel> = mutableListOf(),
        var errors: MutableList<IError> = mutableListOf(),
        var status: CorStatus = CorStatus.NONE,
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
     * @param error Ошибка, которую необходимо добавить в контекст
     * @param failingStatus Необходимо ли установить статус выполнения в FAILING (true/false)
     */
    fun addError(error: IError, failingStatus: Boolean = true) = apply {
        if (failingStatus) status = CorStatus.FAILING
        errors.add(error)
    }


    fun addError(
            e: Throwable,
            level: IError.Level = IError.Level.ERROR,
            field: String = "",
            failingStatus: Boolean = true
    ) {
        addError(CommonErrorModel(e, field = field, level = level), failingStatus)
    }
}