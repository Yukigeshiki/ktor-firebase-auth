package io.robothouse.config.firebase

import io.robothouse.auth.firebase.FirebaseAuthenticationProvider
import io.robothouse.model.User
import kotlinx.coroutines.runBlocking

/**
 * Configuration for [FirebaseAuthenticationProvider].
 */
object AuthConfig {
  fun FirebaseAuthenticationProvider.Configuration.configure() {
    principal = { uid ->
      //this is where you'd make a db call to fetch your User profile
      runBlocking { User(uid, "myUsername") }
    }
  }
}