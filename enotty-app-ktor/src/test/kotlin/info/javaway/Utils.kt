package info.javaway

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.models.NoteIdModel
import kotlin.test.assertEquals
import kotlin.test.assertTrue

object Utils {
    val mapper = jacksonObjectMapper()

    fun <T : List<*>> assertListEquals(expected: T, actual: T, checkOrder: Boolean, message: String? = null) {
        if (checkOrder) {
            assertEquals(expected, actual, message)
        } else {
            assertTrue(
                expected.size == actual.size && expected.containsAll(actual) && actual.containsAll(expected),
                "Expected equal unordered list <$expected>, actual <$actual>."
            )
        }
    }

    val stubDebug = BaseDebugRequest(mode = BaseDebugRequest.Mode.STUB, stubCase = BaseDebugRequest.StubCase.SUCCESS)

    val stubResponseNote = ResponseNote(
            title = Note.getModel().title,
            content = Note.getModel().content,
            parentId = Note.getModel().parentId.asString(),
            role =  ResponseNote.Role.valueOf(Note.getModel().role.name),
            color = Note.getModel().color,
            extendedMode = Note.getModel().extendedMode,
            icon = Note.getModel().icon,
            hidden = Note.getModel().isHidden,
            favorite = Note.getModel().isFavorite,
            showTitle = Note.getModel().isShowTitle,
            createdAt = Note.getModel().createdAt.toEpochMilli().toBigDecimal(),
            updatedAt = Note.getModel().updatedAt.toEpochMilli().toBigDecimal(),
            userUid = Note.getModel().userUid.asString(),
            id = Note.getModel().id.asString(),
            permissions = Note.getModel().permissions.map { NotePermissions.valueOf(it.toString()) }.toSet(),
    )

    val stubCreatableNote = CreatableNote(
            title = stubResponseNote.title,
            content = stubResponseNote.content,
            parentId = stubResponseNote.parentId,
            role = CreatableNote.Role.valueOf(Note.getModel().role.name),
            color = stubResponseNote.color,
            extendedMode = stubResponseNote.extendedMode,
            icon = stubResponseNote.icon,
            hidden = stubResponseNote.hidden,
            favorite = stubResponseNote.favorite,
            showTitle = stubResponseNote.showTitle,
            createdAt = stubResponseNote.createdAt,
            updatedAt = stubResponseNote.updatedAt,
            userUid = stubResponseNote.userUid)

    val stubUpdatableNote = UpdatableNote(
            title = Note.getModel().title,
            content = Note.getModel().content,
            parentId = Note.getModel().parentId.asString(),
            role = UpdatableNote.Role.valueOf(Note.getModel().role.name),
            color = Note.getModel().color,
            extendedMode = Note.getModel().extendedMode,
            icon = Note.getModel().icon,
            hidden = Note.getModel().isHidden,
            favorite = Note.getModel().isFavorite,
            showTitle = Note.getModel().isShowTitle,
            createdAt = Note.getModel().createdAt.toEpochMilli().toBigDecimal(),
            updatedAt = Note.getModel().updatedAt.toEpochMilli().toBigDecimal(),
            userUid = Note.getModel().userUid.asString(),
            id = Note.getModel().id.asString(),
    )
}
