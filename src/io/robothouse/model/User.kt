package io.robothouse.model

import io.ktor.auth.*

data class User(
  val _id: String,
  val username: String
): Principal
