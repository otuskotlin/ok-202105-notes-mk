package info.javaway.enotty.backend.logic.chains.stubs

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.exceptions.EnottyStubCaseNotFound
import info.javaway.enotty.backend.common.models.EnottyStubCase
import info.javaway.enotty.backend.common.models.IError
import info.javaway.enotty.backend.cor.ICorChainDsl
import info.javaway.enotty.backend.cor.chain
import info.javaway.enotty.backend.cor.handlers.chain
import info.javaway.enotty.backend.cor.handlers.worker

internal fun ICorChainDsl<EnottyContext>.noteDeleteStub(title: String) = chain {
    this.title = title
    on{status == CorStatus.RUNNING && stubCase != EnottyStubCase.NONE}

    worker {
        this.title = "Успешный стабкейс для DELETE"
        on{stubCase == EnottyStubCase.SUCCESS}
        handle {
            responseNote = Note.getModel().copy(id = requestNoteId)
            status = CorStatus.FINISHING
        }
    }

    worker{
        this.title= "Обработка отсутствия подходящего стабкейса"
        on{status == CorStatus.RUNNING}
        handle{
            status = CorStatus.FAILING
            addError(e = EnottyStubCaseNotFound(stubCase.name))
        }
    }
}