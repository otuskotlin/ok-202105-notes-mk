package info.javaway.enotty.backend.cor.handlers

import info.javaway.enotty.backend.cor.*

/**
 * Функция расширения, добавляющая в билдер звена цепи дополнительные билдер вложенного звена (?)
 */
fun <T> ICorChainDsl<T>.chain(function: CorChainDsl<T>.() -> Unit) {
    add(CorChainDsl<T>().apply(function))
}

/**
 * Класс, представляющий звено цепочки.
 *
 * @param execs список обработчиков операций.
 * @param title имя звена цепи.
 * @param description описание звена цепи.
 * @param blockOn лямбда, отвечающая за принятие решения о запуске обработчика.
 * @param blockExcept лямбда, отвечающая за обработку ошибок.
 */
class CorChain<T>(
        private val execs: List<ICorExec<T>>,
        override val title: String,
        override val description: String,
        val blockOn: T.() -> Boolean = { true },
        val blockExcept: T.(Throwable) -> Unit = {}
) : ICorWorker<T> {
    override suspend fun on(context: T): Boolean = blockOn(context)

    override suspend fun except(context: T, e: Throwable) = blockExcept(context, e)

    override suspend fun handle(context: T) {
        execs.forEach { it.exec(context) }
    }
}

/**
 * Класс, отвечающий за узел Dsl, в котором создаётся [CorChain]
 */
@CorDslMarker
class CorChainDsl<T>(
        override var title: String = "",
        override var description: String = "",
        private val workers: MutableList<ICorExecDsl<T>> = mutableListOf(),
        private var blockOn: T.() -> Boolean = {true},
        private var blockExcept: T.(e: Throwable) -> Unit = {e: Throwable -> throw e}
) : ICorChainDsl<T> {

    override fun build(): ICorExec<T> = CorChain(
            title = title,
            description = description,
            execs = workers.map { it.build() },
            blockOn = blockOn,
            blockExcept = blockExcept
    )

    override fun add(worker: ICorExecDsl<T>) {
        workers.add(worker)
    }

    override fun on(function: T.() -> Boolean) {
        blockOn = function
    }

    override fun except(function: T.(e: Throwable) -> Unit) {
        blockExcept = function
    }
}