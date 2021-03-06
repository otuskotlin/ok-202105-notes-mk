package info.javaway.enotty.backend.transport.mapping.kmp

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.exceptions.EnottyOperationsNotSet
import info.javaway.enotty.backend.common.models.*

fun EnottyContext.toInitResponse() = InitNoteResponse(
        requestId = onRequest.takeIf { it.isNotBlank() },
        errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
        result = if (errors.find { it.level == IError.Level.ERROR } == null) InitNoteResponse.Result.SUCCESS
        else InitNoteResponse.Result.ERROR
)

fun EnottyContext.toReadResponse() = ReadNoteResponse(
        requestId = onRequest.takeIf { it.isNotBlank() },
        errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
        readNote = responseNote.takeIf { it != NoteModel() }?.toTransport(),
        result = if (errors.find { it.level == IError.Level.ERROR } == null) ReadNoteResponse.Result.SUCCESS
        else ReadNoteResponse.Result.ERROR
)

fun EnottyContext.toCreateResponse() = CreateNoteResponse(
        requestId = onRequest.takeIf { it.isNotBlank() },
        errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
        createdNote = responseNote.takeIf { it != NoteModel() }?.toTransport(),
        result = if (errors.find { it.level == IError.Level.ERROR } == null) CreateNoteResponse.Result.SUCCESS
        else CreateNoteResponse.Result.ERROR
)

fun EnottyContext.toUpdateResponse() = UpdateNoteResponse(
        requestId = onRequest.takeIf { it.isNotBlank() },
        errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
        updateNote = responseNote.takeIf { it != NoteModel() }?.toTransport(),
        result = if (errors.find { it.level == IError.Level.ERROR } == null) UpdateNoteResponse.Result.SUCCESS
        else UpdateNoteResponse.Result.ERROR
)

fun EnottyContext.toDeleteResponse() = DeleteNoteResponse(
        requestId = onRequest.takeIf { it.isNotBlank() },
        errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
        deletedNote = responseNote.takeIf { it != NoteModel() }?.toTransport(),
        result = if (errors.find { it.level == IError.Level.ERROR } == null) DeleteNoteResponse.Result.SUCCESS
        else DeleteNoteResponse.Result.ERROR
)

fun EnottyContext.toSearchResponse() = SearchNoteResponse(
        requestId = onRequest.takeIf { it.isNotBlank() },
        errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
        foundNotes = responseNotes.takeIf { it.isNotEmpty() }?.filter { it != NoteModel() }?.map { it.toTransport() },
        page = responsePage.takeIf { it != PaginatedModel() }?.toTransport(),
        result = if (errors.find { it.level == IError.Level.ERROR } == null) SearchNoteResponse.Result.SUCCESS
        else SearchNoteResponse.Result.ERROR
)

fun EnottyContext.toResponse() = when(operation){
        EnottyContext.EnottyOperations.INIT -> toInitResponse()
        EnottyContext.EnottyOperations.CREATE -> toCreateResponse()
        EnottyContext.EnottyOperations.READ -> toReadResponse()
        EnottyContext.EnottyOperations.UPDATE-> toUpdateResponse()
        EnottyContext.EnottyOperations.DELETE -> toDeleteResponse()
        EnottyContext.EnottyOperations.SEARCH -> toSearchResponse()
        EnottyContext.EnottyOperations.NONE ->
                throw EnottyOperationsNotSet("Operation for error response is not set")
}

private fun PaginatedModel.toTransport() = BasePaginatedResponse(
        size = size.takeIf { it != Int.MIN_VALUE },
        lastId = lastId.takeIf { it != NoteIdModel.NONE }?.asString(),
        position = position.takeIf { it != PaginatedModel.PositionModel.NONE }
                ?.let { BasePaginatedResponse.Position.valueOf(it.name) }
)

private fun IError.toTransport() = RequestError(
        message = message.takeIf { it.isNotBlank() },
        field = field.takeIf { it.isNotBlank() },
)


private fun NoteModel.toTransport() = ResponseNote(
        id = id.takeIf { it != NoteIdModel.NONE }?.asString(),
        title = title.takeIf { it.isNotBlank() },
        content = content.takeIf { it.isNotBlank() },
        parentId = parentId.takeIf { it != NoteIdModel.NONE }?.asString(),
        role = role.takeIf { it != Role.NONE }?.let { ResponseNote.Role.valueOf(it.name) },
        color = color.takeIf { it != 0 },
        extendedMode = extendedMode,
        icon = icon.takeIf { it.isNotBlank() },
        hidden = isHidden,
        favorite = isFavorite,
        showTitle = isShowTitle,
        createdAt = createdAt.toEpochMilli().toBigDecimal(),
        updatedAt = updatedAt.toEpochMilli().toBigDecimal(),
        userUid = userUid.takeIf { it != UserUidModel.NONE }?.asString(),
        permissions = permissions.takeIf { it.isNotEmpty() }?.filter { it != PermissionModel.NONE }
                ?.map { NotePermissions.valueOf(it.name) }?.toSet()
)
