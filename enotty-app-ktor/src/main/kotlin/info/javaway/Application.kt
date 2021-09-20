package info.javaway

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import info.javaway.services.NoteService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*

// function with config (application.conf)
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

//// If you don't want to use config
//object KtorEmbedded {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        io.ktor.server.engine.embeddedServer(Netty, port = 8000) {
//            module()
//        }.start(wait = true)
//    }
//}

@Suppress("UNUSED_PARAMETER") // Referenced in application.conf
@JvmOverloads
fun Application.module() {
    val noteService = NoteService()

    install(DefaultHeaders)
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost()
    }
    install(ContentNegotiation){
        jackson {
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

            enable(SerializationFeature.INDENT_OUTPUT)
            writerWithDefaultPrettyPrinter()
        }
    }
    install(AutoHeadResponse)
    install(Routing)


    routing {
        get("/") {
            call.respondText("Hello, Kotlin!")
        }
        note(noteService)
//
//        static("static"){
//            resources("static")
//        }
    }
}
