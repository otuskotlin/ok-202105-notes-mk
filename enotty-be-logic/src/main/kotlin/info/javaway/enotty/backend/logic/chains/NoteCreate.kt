package info.javaway.enotty.backend.logic.chains

import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.cor.ICorExec
import info.javaway.enotty.backend.cor.chain
import info.javaway.enotty.backend.logic.chains.stubs.noteCreateStub
import info.javaway.enotty.backend.logic.workers.answerPrepareChain
import info.javaway.enotty.backend.logic.workers.chainInitWorker
import info.javaway.enotty.backend.logic.workers.checkOperationWorker

/**
 * Класс-фасад, содержащий все методы бизнес-логики
 */
object NoteCreate : ICorExec<EnottyContext> by chain<EnottyContext>({
    checkOperationWorker(
            title = "Проверка операции",
            targetOperation = EnottyContext.EnottyOperations.CREATE
    )

    chainInitWorker(title = "Инициализация чейна")
    // TODO: валидация
    noteCreateStub(title = "Обработка стабкейса для CREATE")
    // TODO: продовая логика, работа с бд

    answerPrepareChain(title = "Подготовка ответа")
}).build()