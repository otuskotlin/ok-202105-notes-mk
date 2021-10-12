package info.javaway.enotty.backend.logic.chains

import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.cor.ICorExec
import info.javaway.enotty.backend.cor.chain
import info.javaway.enotty.backend.logic.chains.stubs.noteUpdateStub
import info.javaway.enotty.backend.logic.workers.answerPrepareChain
import info.javaway.enotty.backend.logic.workers.chainInitWorker
import info.javaway.enotty.backend.logic.workers.checkOperationWorker

object NoteUpdate : ICorExec<EnottyContext> by chain<EnottyContext>({
    checkOperationWorker(
            title = "Проверка операции",
            targetOperation = EnottyContext.EnottyOperations.UPDATE
    )
    chainInitWorker(title = "Инициализация чейна")
    // TODO: валидация
    noteUpdateStub(title = "Обработка стабкейса для UPDATE")
    // TODO: продовая логика, работа с бд

    answerPrepareChain(title = "Подготовка ответа")
}).build()