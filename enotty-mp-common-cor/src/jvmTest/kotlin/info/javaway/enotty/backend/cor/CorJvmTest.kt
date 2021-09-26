package info.javaway.enotty.backend.cor

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class CorJvmTest {
    @Test
    fun corTest() {
        val chain = CorBaseTest.chain
        val ctx = TestContext(some = 22)

        runBlocking { chain.exec(ctx) }
        assertEquals(26, ctx.some)
    }
}