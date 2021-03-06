package info.javaway.enotty.backend.transport.mapping.kmp

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.models.*
import java.time.Instant
import java.util.*

fun EnottyContext.setQuery(query: InitNoteRequest) = apply {
    operation = EnottyContext.EnottyOperations.INIT
    onRequest = query.requestId.orEmpty()
}

fun EnottyContext.setQuery(query: CreateNoteRequest) = apply {
    operation = EnottyContext.EnottyOperations.CREATE
    onRequest = query.requestId.orEmpty()
    requestNote = query.createdNote?.toModel() ?: NoteModel()
}

fun EnottyContext.setQuery(query: ReadNoteRequest) = apply {
    operation = EnottyContext.EnottyOperations.READ
    onRequest = query.requestId.orEmpty()
    requestNoteId = NoteIdModel(query.readNoteId.orEmpty())
}

fun EnottyContext.setQuery(query: UpdateNoteRequest) = apply{
    operation = EnottyContext.EnottyOperations.UPDATE
    onRequest = query.requestId.orEmpty()
    requestNote = query.createNote?.toModel() ?: NoteModel()
}

fun EnottyContext.setQuery(query: DeleteNoteRequest) = apply{
    operation = EnottyContext.EnottyOperations.DELETE
    onRequest = query.requestId.orEmpty()
    requestNoteId = NoteIdModel(query.deleteNoteId.orEmpty())
}

fun EnottyContext.setQuery(query: SearchNoteRequest) = apply{
    operation = EnottyContext.EnottyOperations.SEARCH
    onRequest = query.requestId.orEmpty()
    requestPage = query.page?.toModel() ?: PaginatedModel()
}

private fun BasePaginatedRequest.toModel() = PaginatedModel(
        size = size ?: Int.MIN_VALUE,
        lastId = NoteIdModel(lastId.orEmpty())
)

private fun UpdatableNote.toModel() = NoteModel(
        id = NoteIdModel(id.orEmpty()),
        title = title.orEmpty(),
        content = content.orEmpty(),
        parentId = NoteIdModel(parentId.orEmpty()),
        role = role?.let { Role.valueOf(it.name) } ?: Role.NONE,
        color = color ?: 0,
        extendedMode = extendedMode ?: false,
        icon = icon.orEmpty(),
        isHidden = hidden ?: false,
        isFavorite = favorite ?: false,
        isShowTitle = showTitle ?: false,
        createdAt = createdAt?.let { Instant.ofEpochMilli(it.toLong()) } ?: Instant.now(),
        updatedAt = updatedAt?.let { Instant.ofEpochMilli(it.toLong()) } ?: Instant.now(),
        userUid = UserUidModel(userUid.orEmpty()),
)

private fun CreatableNote.toModel() = NoteModel(
        title = title.orEmpty(),
        content = content.orEmpty(),
        parentId = NoteIdModel(parentId.orEmpty()),
        role = role?.let { Role.valueOf(it.name) } ?: Role.NONE,
        color = color ?: 0,
        extendedMode = extendedMode ?: false,
        icon = icon.orEmpty(),
        isHidden = hidden ?: false,
        isFavorite = favorite ?: false,
        isShowTitle = showTitle ?: false,
        createdAt = createdAt?.let { Instant.ofEpochMilli(it.toLong()) } ?: Instant.now(),
        updatedAt = updatedAt?.let { Instant.ofEpochMilli(it.toLong()) } ?: Instant.now(),
        userUid = UserUidModel(userUid.orEmpty()),
)