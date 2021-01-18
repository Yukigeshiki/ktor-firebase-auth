val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
  application
  kotlin("jvm") version "1.4.21"
}

group = "io.robothouse.ktorfirebaseauth"
version = "0.0.1"

application {
  mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
  mavenLocal()
  jcenter()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
  sourceCompatibility = "1.8"
  targetCompatibility = "1.8"

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  // Kotlin
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

  // Ktor
  implementation("ch.qos.logback:logback-classic:$logback_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("io.ktor:ktor-auth:$ktor_version")
  implementation("io.ktor:ktor-gson:$ktor_version")
  testImplementation("io.ktor:ktor-server-tests:$ktor_version")

  // Logging
  implementation("org.slf4j:slf4j-simple:1.7.30")

  // Firebase
  implementation("com.google.firebase:firebase-admin:7.1.0")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")