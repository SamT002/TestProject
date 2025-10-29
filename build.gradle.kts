plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    google {
        content {
            includeGroupByRegex("com\\.google.*")
        }
    }
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")


    testImplementation(kotlin("test"))
    // Mockito (https://www.vogella.com/tutorials/Mockito/article.html)
    testImplementation("org.mockito:mockito-core:4.2.0")
    testImplementation("org.mockito:mockito-inline:4.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}