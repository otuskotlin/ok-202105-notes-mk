import info.javaway.Utils
import info.javaway.android.enotty.openapi.models.CreateNoteResponse
import info.javaway.module
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.Assert.fail
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ValidationTest {
    @Test
    fun `bad json`() {
        withTestApplication(Application::module){
            handleRequest(HttpMethod.Post, "/note/create"){
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.withCharset(Charsets.UTF_8).toString())
                setBody("{")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println("|$jsonString|")

                val res = Utils.mapper.readValue(response.content, CreateNoteResponse::class.java)

                assertEquals(CreateNoteResponse.Result.ERROR, res.result)
                assertTrue{
                    res.errors?.find { it.message?.lowercase()?.contains("unexpected end-of-input") == true } != null
                }
            }
        }
    }
}