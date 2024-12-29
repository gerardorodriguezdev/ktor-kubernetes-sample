import io.ktor.plugin.features.*

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val ktorSampleVersion = "1.0.0"

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
}

group = "com.example"
version = ktorSampleVersion

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-cio-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-client-cio")

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

ktor {
    docker {
        localImageName.set("ktor-sample-image")
        imageTag.set(ktorSampleVersion)
        externalRegistry.set(
            DockerImageRegistry.externalRegistry(
                username = provider { findProperty("GITHUB_ACTOR") as String },
                password = provider { findProperty("GITHUB_TOKEN") as String },

                hostname = provider { "ghcr.io" },
                namespace = provider { findProperty("GITHUB_ACTOR") as String },
                project = provider { "ktor-sample-image" },
            )
        )
    }
}