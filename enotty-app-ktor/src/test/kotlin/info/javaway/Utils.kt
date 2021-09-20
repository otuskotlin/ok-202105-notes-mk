package info.javaway

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import info.javaway.android.enotty.openapi.models.BaseDebugRequest
import info.javaway.android.enotty.openapi.models.NotePermissions
import info.javaway.android.enotty.openapi.models.ResponseNote
import info.javaway.android.enotty.openapi.models.UpdatableNote
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

    val stubDebug = BaseDebugRequest(mode = BaseDebugRequest.Mode.STUB)

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
