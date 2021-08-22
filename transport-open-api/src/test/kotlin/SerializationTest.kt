import com.fasterxml.jackson.databind.ObjectMapper
import info.javaway.android.enotty.openapi.models.BaseMessage
import info.javaway.android.enotty.openapi.models.CreatableNote
import info.javaway.android.enotty.openapi.models.CreateNoteRequest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {

    private val requestId = "0000"
    private val icon = "default icon"
    private val password = "secret"
    private val createRequest = CreateNoteRequest(
            requestId = requestId,
            createdNote = CreatableNote(
                    title = "folder title",
                    role = CreatableNote.Role.FOLDER,
                    password = password,
                    icon = icon
            )
    )
    private val om = ObjectMapper()

    @Test
    fun serializationTest(){
        val json = om.writeValueAsString(createRequest)
        println(json)

        assertTrue("json must contain descriminator"){
            json.contains(""""messageType":"${createRequest::class.simpleName}"""")
        }
        assertTrue("json must serialize visibility icon"){
            json.contains(""""icon":"$icon"""")
        }
        assertTrue("json must serialize messageId field"){
            json.contains(""""password":"$password"""")
        }
    }

    @Test
    fun deserializeTest(){
        val json = om.writeValueAsString(createRequest)
        val deserialized = om.readValue(json, BaseMessage::class.java) as CreateNoteRequest

        assertEquals(CreatableNote.Role.FOLDER, deserialized.createdNote?.role)
        assertEquals(icon, deserialized.createdNote?.icon)
        assertEquals(requestId, deserialized.requestId)
    }
}