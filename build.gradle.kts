plugins {
    id("java")
    kotlin("jvm") version "1.7.10"
    kotlin("kapt") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.json:json:20210307")

    testImplementation("junit:junit:4.+")

    testImplementation(kotlin("test"))

    implementation("com.google.code.gson:gson:2.8.7")

    implementation("com.squareup.moshi:moshi:1.13.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("io.netty:netty:3.10.6.Final")
    implementation("io.netty:netty-all:4.1.85.Final")
    implementation("com.google.protobuf:protobuf-java:2.6.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}