package info.javaway.enotty.backend.logic.workers

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.common.exceptions.EnottyIllegalOperation
import info.javaway.enotty.backend.cor.ICorChainDsl
import info.javaway.enotty.backend.cor.handlers.worker

internal fun ICorChainDsl<EnottyContext>.checkOperationWorker(
        targetOperation: EnottyContext.EnottyOperations,
        title: String
) = worker {
    this.title = title
    description = "Если в контексте недопустимая операция, то чейн неуспешен"
    on{ operation != targetOperation }
    handle {
        status = CorStatus.FAILING
        addError(
                e = EnottyIllegalOperation("Expected ${targetOperation.name} but was ${operation.name}")
        )
    }
}