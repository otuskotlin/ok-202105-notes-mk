package info.javaway.enotty.backend.validation.workers

import info.javaway.enotty.backend.common.context.CorStatus
import info.javaway.enotty.backend.cor.ICorChainDsl
import info.javaway.enotty.backend.cor.handlers.worker
import info.javaway.enotty.backend.validation.cor.ValidationDSL

fun <C> ICorChainDsl<C>.validation(block: ValidationDSL<C>.() -> Unit){
    worker {
        this.title = "Validation"
        description = "Validation of logic"
//        on{status == CorStatus.RUNNING}
//        except { status = CorStatus.FAILING }
        handle{
            ValidationDSL<C>().apply(block).build().exec(this)
        }
    }
}