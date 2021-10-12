package info.javaway.enotty.backend.logic.chains

import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.cor.ICorExec
import info.javaway.enotty.backend.cor.chain
import info.javaway.enotty.backend.logic.chains.stubs.noteReadStub
import info.javaway.enotty.backend.logic.workers.answerPrepareChain
import info.javaway.enotty.backend.logic.workers.chainInitWorker
import info.javaway.enotty.backend.logic.workers.checkOperationWorker

object NoteRead : ICorExec<EnottyContext> by chain<EnottyContext>({
    checkOperationWorker(
            title = "Проверка операции",
            targetOperation = EnottyContext.EnottyOperations.READ
    )
    chainInitWorker(title = "Инициализация чейна")
    // TODO: валидация
    noteReadStub(title = "Обработка стабкейса для READ")
    // TODO: продовая логика, работа с бд

    answerPrepareChain(title = "Подготовка ответа")
}).build()