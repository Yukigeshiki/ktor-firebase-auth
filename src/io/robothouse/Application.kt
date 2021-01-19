package io.robothouse

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.robothouse.auth.firebase.firebase
import io.robothouse.config.firebase.AuthConfig.configure
import io.robothouse.config.firebase.FirebaseAdmin
import io.robothouse.model.User
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

  // initialize Firebase Admin SDK
  FirebaseAdmin.init()

  install(ContentNegotiation) { gson { setPrettyPrinting() } }
  install(Authentication) { firebase { configure() } }
  install(CallLogging) {
    level = Level.INFO
    filter { call -> call.request.path().startsWith("/") }
  }

  routing {
    get("/") {
      call.respond(HttpStatusCode.OK, "I'm working just fine, thanks!")
    }

    authenticate {
      get("/authenticated") {
        call.respond(HttpStatusCode.OK, "My name is ${call.principal<User>()?.username}, and I'm authenticated!")
      }
    }
  }
}
