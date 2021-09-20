import info.javaway.enotty.backend.common.models.*
import java.time.Instant
import java.util.*

object Note {
    private const val stubId = "note_stub_id"
    private val stubReady = NoteModel(
            id = NoteIdModel(id = stubId),
            title = "Моя заметка",
            content = "Заметка о каких-то важных вещах",
            parentId = NoteIdModel(id = "root_folder"),
            role = Role.NOTE,
            color = 0x444444,
            extendedMode = false,
            icon = "default icon",
            isHidden = false,
            isFavorite = false,
            isShowTitle = false,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            userUid = UserUidModel(uid = "asdfjklewr"),
            permissions = mutableSetOf(PermissionModel.SHARE)
    )

    private val stubInProgress = NoteModel(
            id = NoteIdModel(id = "4343"),
            title = "Ещё одна заметка",
            content = "Очень важное содержание",
            parentId = NoteIdModel(id = "root folder"),
            role = Role.NOTE,
            color = 0,
            extendedMode = false,
            icon = "",
            isHidden = false,
            isFavorite = false,
            isShowTitle = false,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            userUid = UserUidModel(uid = ""),
            permissions = mutableSetOf()
    )

    fun getModel(model: (NoteModel.() -> Unit)? = null) = stubReady.also { stub ->
        model?.let { stub.apply(it) }
    }

    fun isCorrectedId(id: String) = id == stubId

    fun getModels() = listOf(
            stubReady,
            stubInProgress
    )

    fun NoteModel.update(updatableNote: NoteModel) = apply {
        id = updatableNote.id
        title = updatableNote.title
        content = updatableNote.content
        parentId = updatableNote.parentId
        role = updatableNote.role
        color = updatableNote.color
        extendedMode = updatableNote.extendedMode
        icon = updatableNote.icon
        isHidden = updatableNote.isHidden
        isFavorite = updatableNote.isFavorite
        isShowTitle = updatableNote.isShowTitle
        createdAt = updatableNote.createdAt
        updatedAt = updatableNote.updatedAt
        userUid = updatableNote.userUid
        permissions.addAll(updatableNote.permissions)
    }
}