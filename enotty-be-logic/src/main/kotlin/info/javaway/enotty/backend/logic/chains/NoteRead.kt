package info.javaway.enotty.backend.logic.chains

import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.cor.ICorExec
import info.javaway.enotty.backend.cor.chain
import info.javaway.enotty.backend.logic.chains.helpers.enottyValidation
import info.javaway.enotty.backend.logic.chains.stubs.noteReadStub
import info.javaway.enotty.backend.logic.workers.answerPrepareChain
import info.javaway.enotty.backend.logic.workers.chainInitWorker
import info.javaway.enotty.backend.logic.workers.checkOperationWorker
import info.javaway.enotty.backend.validation.validators.ValidatorStringNonEmpty

object NoteRead : ICorExec<EnottyContext> by chain<EnottyContext>({
    checkOperationWorker(
            title = "Проверка операции",
            targetOperation = EnottyContext.EnottyOperations.READ
    )
    chainInitWorker(title = "Инициализация чейна")

    enottyValidation {
        validate<String?>{
            onValue { requestNote.id.asString() }
            validator(ValidatorStringNonEmpty(field = "id"))
        }
    }
    noteReadStub(title = "Обработка стабкейса для READ")
    // TODO: продовая логика, работа с бд

    answerPrepareChain(title = "Подготовка ответа")
}).build()