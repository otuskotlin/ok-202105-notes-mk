package info.javaway.enotty.backend.cor.handlers

import info.javaway.enotty.backend.cor.*

/**
 * Функция, создающая DSL билдер воркера, применяющая к нему блок block и добавляющая его в
 * список воркеров создаваемого чейна.
 *
 * @param block блок кода с конфигурацией создаваемого воркера.
 */
@CorDslMarker
fun <T> ICorChainDsl<T>.worker(block: CorWorkerDsl<T>.() -> Unit) {
    add(CorWorkerDsl<T>().apply(block))
}

/**
 * Функция, создающая DSL билдер воркера, применяющая к нему набор параметров и добавляющая его в
 * список воркеров создаваемого чейна.
 *
 * @param title заголовок создаваемого воркера.
 * @param description описание создаваемого воркера.
 * @param blockHandle блок кода с конфигурацией создаваемого воркера.
 */
@CorDslMarker
fun <T> ICorChainDsl<T>.worker(
        title: String,
        description: String = "",
        blockHandle: T.() -> Unit
) {
    add(CorWorkerDsl(
            title = title,
            description = description,
            blockHandle = blockHandle
    ))
}

/**
 * Класс обработчик.
 */
class CorWorker<T>(
        override val title: String,
        override val description: String = "",
        val blockOn: T.() -> Boolean = { true },
        val blockHandle: T.() -> Unit = {},
        val blockExcept: T.(Throwable) -> Unit = {}
) : ICorWorker<T> {
    override suspend fun on(context: T): Boolean = blockOn(context)
    override suspend fun handle(context: T) = blockHandle(context)
    override suspend fun except(context: T, e: Throwable) = blockExcept(context, e)
}

/**
 * DSL билдер воркера [CorWorker].
 */
@CorDslMarker
class CorWorkerDsl<T>(
        override var title: String = "",
        override var description: String = "",
        private var blockOn: T.() -> Boolean = { true },
        private var blockHandle: T.() -> Unit = {},
        private var blockExcept: T.(e: Throwable) -> Unit = { e: Throwable -> throw e }
) : ICorWorkerDsl<T> {
    override fun build(): ICorExec<T> = CorWorker(
            title = title,
            description = description,
            blockOn = blockOn,
            blockHandle = blockHandle,
            blockExcept = blockExcept
    )

    override fun handle(function: T.() -> Unit) {
        blockHandle = function
    }

    override fun on(function: T.() -> Boolean) {
        blockOn = function
    }

    override fun except(function: T.(e: Throwable) -> Unit) {
        blockExcept = function
    }
}