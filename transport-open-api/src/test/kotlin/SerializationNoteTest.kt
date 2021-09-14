import com.fasterxml.jackson.databind.ObjectMapper
import info.javaway.android.enotty.openapi.models.BaseMessage
import info.javaway.android.enotty.openapi.models.CreatableNote
import info.javaway.android.enotty.openapi.models.CreateNoteRequest
import info.javaway.android.enotty.openapi.models.ResponseNote
import org.junit.Test
import java.util.*
import kotlin.test.assertContains
import kotlin.test.assertEquals

class SerializationNoteTest {

    private val jsonSerializer = ObjectMapper()
    private val dto = CreateNoteRequest(
            requestId = "0000",
            createdNote = CreatableNote(
                    title = "test note",
                    content = "test content",
                    role = CreatableNote.Role.NOTE,
                    favorite = true,
                    hidden = false,
                    parentId = "",
                    color = 0,
                    extendedMode = false,
                    icon = "",
                    showTitle = true,
                    createdAt = Date().time.toBigDecimal(),
                    updatedAt = Date().time.toBigDecimal(),
                    userUid = "",
            )
    )

    @Test
    fun noteDiscriminatorTest() {
        val serializedString = jsonSerializer.writeValueAsString(dto)
        assertContains(serializedString, Regex("content\":\\s*\"test content"))
    }

    @Test
    fun noteDeserializationTest() {
        val serializedString = jsonSerializer.writeValueAsString(dto)
        val deserializedDto = jsonSerializer.readValue(serializedString, BaseMessage::class.java)
        assertEquals(ResponseNote.Role.NOTE.value, (deserializedDto as CreateNoteRequest).createdNote?.role?.value)
    }
}