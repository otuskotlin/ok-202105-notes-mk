package info.javaway.enotty.controllers

import info.javaway.Utils
import info.javaway.Utils.assertListEquals
import info.javaway.android.enotty.openapi.models.*
import junit.framework.Assert.assertNull
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class NoteRouterTest : RouterTest() {

    @Test
    fun testPostNoteCreate() {
        val data = CreateNoteRequest(debug = Utils.stubDebug)

        testPostRequest<CreateNoteResponse>(data, "/note/create") {
            assertEquals(CreateNoteResponse.Result.SUCCESS, result)
            assertNull(errors)
            assertEquals(Utils.stubResponseNote, createdNote)
        }
    }

    @Test
    fun testPostNoteRead() {
        val data = ReadNoteRequest(readNoteId = "444", debug = Utils.stubDebug)

        testPostRequest<ReadNoteResponse>(data, "note/read") {
            assertEquals(ReadNoteResponse.Result.ERROR, result)
            assertNotNull(errors)
            errors!!.apply {
                assertTrue(isNotEmpty())
                assertNotNull(find {
                    it.field == "requestedNoteId" && (it.message?.contains("444") ?: false)
                })
            }
        }

        testPostRequest<ReadNoteResponse>(data.copy(readNoteId = "note_stub_id"), "/note/read") {
            assertEquals(ReadNoteResponse.Result.SUCCESS, result)
            assertNull(errors)
            assertEquals(Utils.stubResponseNote, readNote)
        }
    }

    @Test
    fun testPostNoteUpdate() {
        val data = UpdateNoteRequest(createNote = Utils.stubUpdatableNote, debug = Utils.stubDebug)

        testPostRequest<UpdateNoteResponse>(data, "/note/update") {
            assertEquals(UpdateNoteResponse.Result.SUCCESS, result)
            assertNull(errors)
            assertEquals(Utils.stubResponseNote.copy(permissions = null), updateNote)
        }
    }

    @Test
    fun testPostNoteDelete() {
        val data = DeleteNoteRequest(deleteNoteId = "434343", debug = Utils.stubDebug)

        testPostRequest<DeleteNoteResponse>(data, "/note/delete") {
            assertEquals(DeleteNoteResponse.Result.SUCCESS, result)
            assertNotNull(errors)
            errors!!.apply {
                assertTrue(isNotEmpty())
                assertNotNull(find { it.field == "id" })
            }
        }

        testPostRequest<DeleteNoteResponse>(data.copy(deleteNoteId = "note_stub_id"), "/note/delete") {
            assertEquals(DeleteNoteResponse.Result.SUCCESS, result)
            assertNull(errors)
        }
    }

    @Test
    fun testPostNoteSearch() {
        val data = SearchNoteRequest(requestId = "43", page = BasePaginatedRequest(size = 3, lastId = "note_stub_id"))

        testPostRequest<SearchNoteResponse>(data, "/note/search") {
            assertEquals(SearchNoteResponse.Result.SUCCESS, result)
            assertNull(errors)
            assertNotNull(foundNotes)
            assertListEquals(foundNotes!!.map { it.id }, Note.getModels().map { it.id.asString() }, false)
        }
    }
}