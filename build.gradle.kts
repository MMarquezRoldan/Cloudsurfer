plugins {
    kotlin("jvm") version "2.1.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("mysql:mysql-connector-java:8.0.28")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}
