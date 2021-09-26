package info.javaway.enotty.backend.cor

import info.javaway.enotty.backend.cor.handlers.chain
import info.javaway.enotty.backend.cor.handlers.parallel
import info.javaway.enotty.backend.cor.handlers.worker
import kotlin.test.Test

class CorBaseTest {

    @Test
    fun createCor() {

    }

    companion object {
        val chain = chain<TestContext> {
            worker {
                title = "Инициализация статуса"
                description = "При старте обработки цепочки, статус ещё не установлен. Проверяем его"

                on { status == CorStatuses.NONE }
                handle { status = CorStatuses.RUNNING }
                except { status = CorStatuses.ERROR }
            }

            chain {
                on { status == CorStatuses.RUNNING }

                worker(
                        title = "Лябда обработчик",
                        description = "Пример использования обработчика в виде лямбды"
                ) {
                    some += 4
                }
            }

            parallel {
                on { some < 15 }
                worker(title = "Increment some") {
                    some++
                }
            }

            printResult()
        }.build()
    }
}

private fun ICorChainDsl<TestContext>.printResult() = worker(title = "Print Result") {
    println("some $some")
}

data class TestContext(
        var status: CorStatuses = CorStatuses.NONE,
        var some: Int = Int.MIN_VALUE
)

enum class CorStatuses {
    NONE, RUNNING, FAILING, DONE, ERROR
}