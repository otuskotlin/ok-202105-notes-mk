import info.javaway.android.enotty.openapi.models.ResponseNote
import info.javaway.android.enotty.openapi.models.UpdatableNote
import info.javaway.android.enotty.openapi.models.UpdateNoteRequest
import info.javaway.android.enotty.openapi.models.UpdateNoteResponse
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.models.*
import info.javaway.enotty.backend.transport.mapping.kmp.setQuery
import info.javaway.enotty.backend.transport.mapping.kmp.toUpdateResponse
import org.junit.Test
import kotlin.test.assertEquals

class MappingTest {

    @Test
    fun setUpdateQueryMappingTest() {
        val query = UpdateNoteRequest(
                requestId = "1111",
                createNote = UpdatableNote(
                        id = "uid1",
                        title = "tested title",
                        userUid = "some user",
                        icon = "test icon",
                        role = UpdatableNote.Role.NOTE
                )
        )

        val context = EnottyContext().setQuery(query)
        assertEquals("1111", context.onRequest)
        assertEquals(Role.NOTE, context.requestNote.role)
        assertEquals("tested title", context.requestNote.title)
        assertEquals("some user", context.requestNote.userUid.uid)
    }

    @Test
    fun updateResponseMappingTest(){
        val context = EnottyContext(
                onRequest = "123456",
                responseNote = NoteModel(
                        role = Role.NOTE,
                        title = "test title",
                        id = NoteIdModel("test note id"),
                ),
                errors = mutableListOf(CommonErrorModel(level = IError.Level.WARNING))
        )
        val response = context.toUpdateResponse()

        assertEquals("123456", response.requestId)
        assertEquals("test title", response.updateNote?.title)
        assertEquals(ResponseNote.Role.NOTE, response.updateNote?.role)
        assertEquals(UpdateNoteResponse.Result.SUCCESS, response.result)
        assertEquals(1, response.errors?.size)
    }

}