package info.javaway.enotty.backend.cor.handlers

import info.javaway.enotty.backend.cor.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Функция, добавляющая в [CorParallelDsl] вложенное параллельное звено,
 * которое конфигурируется блокном block.
 */
@CorDslMarker
fun <T> ICorChainDsl<T>.parallel(block: CorParallelDsl<T>.() -> Unit){
    add(CorParallelDsl<T>().apply(block))
}

/**
 * Обработчик цепочки, который может быть выполнен параллельно.
 *
 * @param execs список обработчиков операций.
 * @param title имя звена цепи.
 * @param description описание звена цепи.
 * @param blockOn лямбда, отвечающая за принятие решения о запуске обработчика.
 * @param blockExcept лямбда, отвечающая за обработку ошибок.
 */
class CorParallel<T>(
        private val execs: List<ICorExec<T>>,
        override val title: String,
        override val description: String = "",
        val blockOn: T.() -> Boolean = { true },
        val blockExcept: T.(Throwable) -> Unit = {}
) : ICorWorker<T> {
    override suspend fun on(context: T): Boolean = blockOn(context)
    override suspend fun except(context: T, e: Throwable) = blockExcept(context, e)
    override suspend fun handle(context: T): Unit = coroutineScope {
        execs.map { launch { it.exec(context) } }
    }
}


/**
 * DSL билдер для создания чейна [CorParallel].
 *
 * @property title название создаваемого чейна.
 * @property description описание создаваемого чейна.
 * @property workers вложенные воркеры-обработчики.
 * @property blockOn блок кода, отвечающий за необходимость запуска создаваемого чейна.
 * @property blockExcept блок кода, отвечающий за обработку ошибки в создаваемом чейне.
 */
@CorDslMarker
class CorParallelDsl<T>(
        override var title: String = "",
        override var description: String = "",
        private val workers: MutableList<ICorExecDsl<T>> = mutableListOf(),
        private var blockOn: T.() -> Boolean = { true },
        private var blockExcept: T.(e: Throwable) -> Unit = { e: Throwable -> throw e }
) : ICorChainDsl<T> {
    override fun build(): ICorExec<T> = CorParallel(
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