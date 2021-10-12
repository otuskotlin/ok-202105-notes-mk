package info.javaway.enotty.backend.logic.workers

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.common.context.EnottyContext
import info.javaway.enotty.backend.cor.ICorChainDsl
import info.javaway.enotty.backend.cor.handlers.worker

internal fun ICorChainDsl<EnottyContext>.chainInitWorker(title: String) = worker {
    this.title = title
    description = "При старте обработки цепочки статус ещё не установлен. Проверяем его"

    on { status == CorStatus.NONE }

    handle {
        status = CorStatus.RUNNING
    }
}