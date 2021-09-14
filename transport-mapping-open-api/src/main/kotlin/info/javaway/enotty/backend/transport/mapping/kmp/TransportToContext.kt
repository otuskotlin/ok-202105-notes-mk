package info.javaway.enotty.backend.transport.mapping.kmp

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.MpContext
import info.javaway.enotty.backend.common.models.*
import java.text.DateFormat
import java.util.*

fun MpContext.setQuery(query: InitNoteRequest) = apply {
    onRequest = query.requestId.orEmpty()
}

fun MpContext.setQuery(query: CreateNoteRequest) = apply {
    onRequest = query.requestId.orEmpty()
    requestNote = query.createdNote?.toModel() ?: NoteModel()
}

fun MpContext.setQuery(query: ReadNoteRequest) = apply {
    onRequest = query.requestId.orEmpty()
    requestNoteId = NoteIdModel(query.readNoteId.orEmpty())
}

fun MpContext.setQuery(query: UpdateNoteRequest) = apply{
    onRequest = query.requestId.orEmpty()
    requestNote = query.createNote?.toModel() ?: NoteModel()
}

fun MpContext.setQuery(query: DeleteNoteRequest) = apply{
    onRequest = query.requestId.orEmpty()
    requestNoteId = NoteIdModel(query.deleteNoteId.orEmpty())
}

fun MpContext.setQuery(query: SearchNoteRequest) = apply{
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
        createdAt = createdAt?.let { Date(it.toLong()) } ?: Date(),
        updatedAt = updatedAt?.let { Date(it.toLong()) } ?: Date(),
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
        createdAt = createdAt?.let { Date(it.toLong()) } ?: Date(),
        updatedAt = updatedAt?.let { Date(it.toLong()) } ?: Date(),
        userUid = UserUidModel(userUid.orEmpty()),
)