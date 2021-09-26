package info.javaway.enotty.backend.cor

import info.javaway.enotty.backend.cor.handlers.CorChainDsl

/**
 * Интерфейс для построения [ICorExec] в DSL стиле.
 */
interface ICorExecDsl<T> {
    var title: String
    var description: String
    fun build(): ICorExec<T>
}

/**
 * Для чего?
 */
interface ICorChainDsl<T> : ICorExecDsl<T>, ICorHandlerDsl<T> {
    fun add(worker: ICorExecDsl<T>)
}

/**
 * Для чего?
 */
interface ICorWorkerDsl<T> : ICorExecDsl<T>, ICorHandlerDsl<T> {
    fun handle(function: T.() -> Unit)
}

/**
 * Базовый интерфейс, предназначенный для выполнения шага в цепочке.
 */
interface ICorExec<T>{
    /**
     * Функция обработчик в звене цепочки.
     */
    suspend fun exec(context: T)
}

/**
 * Интерфейс, предназначенный для шага выполнения операции в цепочке.
 *
 */
interface ICorWorker<T>: ICorExec<T> {
    val title: String
    val description: String

    /**
     * Условие, от которого зависит, нужно ли выполнять данный шаг.
     *
     * @param context контекст операции.
     * @return результат проверки условия [Boolean]
     */
    suspend fun on(context: T): Boolean

    /**
     * Функция перехвата ошибки.
     *
     * @param context контекст операции.
     * @param e возникшая ошибка.
     */
    suspend fun except(context: T, e: Throwable)

    /**
     * Функция обработчик, которая будет обрабатывать контекст.
     *
     * @param context контекст операции.
     */
    suspend fun handle(context: T)

    override suspend fun exec(context: T) {
        if(on(context)){
            try{
                handle(context)
            } catch (e: Throwable){
                except(context, e)
            }
        }
    }
}

/**
 * Для чего?
 */
interface ICorHandlerDsl<T> {
    fun on(function: T.() -> Boolean)
    fun except(function: T.(e: Throwable) -> Unit)
}

fun <T> chain(function: CorChainDsl<T>.() -> Unit) = CorChainDsl<T>().apply(function)
