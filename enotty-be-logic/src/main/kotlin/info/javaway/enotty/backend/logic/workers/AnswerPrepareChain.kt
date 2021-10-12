package info.javaway.enotty.backend.logic.workers

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.cor.ICorChainDsl
import info.javaway.enotty.backend.cor.handlers.chain
import info.javaway.enotty.backend.cor.handlers.worker

internal fun ICorChainDsl<EnottyContext>.answerPrepareChain(title: String) = chain{
    this.title = title
    description = "Чейн считается успешным, если в нем не было ошибок и он отработал"

    worker {
        this.title = "Обработчик успешного чейна "
        on { status in setOf(CorStatus.RUNNING , CorStatus.FINISHING) }
        handle {
            status = CorStatus.SUCCESS
        }
    }

    worker {
        this.title = "Обработчик неуспешного чейна"
        on{status != CorStatus.SUCCESS}
        handle{
            status = CorStatus.ERROR
        }
    }
}