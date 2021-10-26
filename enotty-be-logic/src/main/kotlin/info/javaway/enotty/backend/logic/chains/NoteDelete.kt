package info.javaway.enotty.backend.logic.chains

import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.cor.ICorExec
import info.javaway.enotty.backend.cor.chain
import info.javaway.enotty.backend.logic.chains.helpers.enottyValidation
import info.javaway.enotty.backend.logic.chains.stubs.noteDeleteStub
import info.javaway.enotty.backend.logic.workers.answerPrepareChain
import info.javaway.enotty.backend.logic.workers.chainInitWorker
import info.javaway.enotty.backend.logic.workers.checkOperationWorker
import info.javaway.enotty.backend.validation.validators.ValidatorStringNonEmpty

object NoteDelete : ICorExec<EnottyContext> by chain<EnottyContext>({
    checkOperationWorker(
            title = "Проверка операции",
            targetOperation = EnottyContext.EnottyOperations.DELETE
    )
    chainInitWorker(title = "Инициализация чейна")

    enottyValidation {
        validate<String?> {
            onValue { requestNote.id.asString() }
            validator(ValidatorStringNonEmpty(field = "id"))
        }
    }

    noteDeleteStub(title = "Обработка стабкейса для DELETE")
    // TODO: продовая логика, работа с бд

    answerPrepareChain(title = "Подготовка ответа")
}).build()