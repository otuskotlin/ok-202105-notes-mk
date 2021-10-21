package info.javaway.enotty.backend.logic

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.models.EnottyStubCase
import info.javaway.enotty.backend.common.models.NoteIdModel
import info.javaway.enotty.backend.common.models.PaginatedModel
import info.javaway.enotty.backend.logic.chains.NoteCrud
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class NoteCrudValidatorTest {

    @Test
    fun createSuccessTest() {
        val crud = NoteCrud()
        val context = EnottyContext(
                stubCase = EnottyStubCase.SUCCESS,
                requestNote = Note.getModel { id = NoteIdModel.NONE },
                operation = EnottyContext.EnottyOperations.CREATE
        )

        runBlocking {
            crud.create(context)
        }

        assertEquals(CorStatus.SUCCESS, context.status)

        val expected = Note.getModel()
        with(context.responseNote){

            assertEquals(expected.id, id)
            assertEquals(expected.content, content)
            assertEquals(expected.icon, icon)
            assertEquals(expected.createdAt, createdAt)
            assertEquals(expected.isHidden, isHidden)
        }
    }

    @Test
    fun readSuccessTest() {
        val crud = NoteCrud()
        val context = EnottyContext(
                stubCase = EnottyStubCase.SUCCESS,
                requestNoteId = Note.getModel().id,
                operation = EnottyContext.EnottyOperations.READ
        )

        runBlocking {
            crud.read(context)
        }

        assertEquals(CorStatus.SUCCESS, context.status)

        val expected = Note.getModel()
        with(context.responseNote){
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.content, content)
            assertEquals(expected.icon, icon)
            assertEquals(expected.permissions, permissions)
            assertEquals(expected.isHidden, isHidden)
        }
    }

    @Test
    fun updateSuccessTest() {
        val crud = NoteCrud()
        val context = EnottyContext(
                stubCase = EnottyStubCase.SUCCESS,
                requestNote = Note.getModel(),
                operation = EnottyContext.EnottyOperations.UPDATE
        )

        runBlocking {
            crud.update(context)
        }

        assertEquals(CorStatus.SUCCESS, context.status)

        val expected = Note.getModel()
        with(context.responseNote){
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.content, content)
            assertEquals(expected.icon, icon)
            assertEquals(expected.permissions, permissions)
            assertEquals(expected.isHidden, isHidden)
        }
    }

    @Test
    fun deleteSuccessTest() {
        val crud = NoteCrud()
        val context = EnottyContext(
                stubCase = EnottyStubCase.SUCCESS,
                requestNoteId = Note.getModel().id,
                operation = EnottyContext.EnottyOperations.DELETE
        )

        runBlocking {
            crud.delete(context)
        }

        assertEquals(CorStatus.SUCCESS, context.status)

        val expected = Note.getModel()
        with(context.responseNote){
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.content, content)
            assertEquals(expected.icon, icon)
            assertEquals(expected.permissions, permissions)
            assertEquals(expected.isHidden, isHidden)
        }
    }

    @Test
    fun searchSuccessTest() {
        val crud = NoteCrud()
        val context = EnottyContext(
                stubCase = EnottyStubCase.SUCCESS,
                requestPage = PaginatedModel(),
                operation = EnottyContext.EnottyOperations.SEARCH
        )

        runBlocking {
            crud.search(context)
        }

        assertEquals(CorStatus.SUCCESS, context.status)

        val expected = Note.getModels()
        with(context.responseNotes){
            assertEquals(expected.size, size)
        }
    }
}