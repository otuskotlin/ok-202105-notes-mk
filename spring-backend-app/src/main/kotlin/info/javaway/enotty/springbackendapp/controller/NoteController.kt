package info.javaway.enotty.springbackendapp.controller

import info.javaway.android.enotty.openapi.models.*
import info.javaway.enotty.backend.common.context.MpContext
import info.javaway.enotty.backend.transport.mapping.kmp.*
import info.javaway.enotty.springbackendapp.service.NoteService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/note")
class NoteController(
        private val noteService: NoteService
) {

    @PostMapping("create")
    fun createNote(@RequestBody createNoteRequest: CreateNoteRequest): CreateNoteResponse {
        val context = MpContext().setQuery(createNoteRequest)
        val response = noteService.createNote(context).toCreateResponse()
        return response
    }

    @PostMapping("read")
    fun readNote(@RequestBody readNoteRequest: ReadNoteRequest) {
        print("wow")
        MpContext().setQuery(readNoteRequest).let {
            noteService.readNote(it)
        }.toReadResponse()
    }

    @RequestMapping("update", method = [RequestMethod.POST])
    fun updateNote(@RequestBody updateNoteRequest: UpdateNoteRequest): UpdateNoteResponse {
        return MpContext().setQuery(updateNoteRequest).let {
            noteService.updateNote(it)
        }.toUpdateResponse()
    }

    @PostMapping("delete")
    fun deleteNote(@RequestBody deleteNoteRequest: DeleteNoteRequest): DeleteNoteResponse {
        val context = MpContext().setQuery(deleteNoteRequest)
        val result = noteService.deleteNote(context)
        return result.toDeleteResponse()
    }

    @PostMapping("search")
    fun searchNote(@RequestBody searchNoteRequest: SearchNoteRequest): SearchNoteResponse {
        return MpContext().setQuery(searchNoteRequest).let {
            noteService.findNote(it)
        }.toSearchResponse()
    }
}