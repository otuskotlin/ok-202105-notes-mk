package info.javaway.enotty.backend.logic.chains

import info.javaway.enotty.backend.common.context.EnottyContext

class NoteCrud {
    suspend fun create(context: EnottyContext){
        NoteCreate.exec(context.initSettings())
    }
    suspend fun read(context: EnottyContext) {
        NoteRead.exec(context.initSettings())
    }
    suspend fun update(context: EnottyContext) {
        NoteUpdate.exec(context.initSettings())
    }
    suspend fun delete(context: EnottyContext) {
        NoteDelete.exec(context.initSettings())
    }
    suspend fun search(context: EnottyContext) {
        NoteSearch.exec(context.initSettings())
    }

    // Метод для установки параметров чейна в контекст, параметры передаются в конструкторе класса
    private fun EnottyContext.initSettings() = apply {  }
}