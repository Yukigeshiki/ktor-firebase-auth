package io.robothouse

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
  install(CallLogging) {
    level = Level.INFO
    filter { call -> call.request.path().startsWith("/") }
  }
  install(ContentNegotiation) { gson { setPrettyPrinting() } }

  install(Authentication) { }

  routing {
    get("/") {
      call.respond(HttpStatusCode.OK, "I'm working just fine, thanks!")
    }
  }
}

