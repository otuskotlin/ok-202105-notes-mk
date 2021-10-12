val ktorVersion: String by project


fun DependencyHandler.ktor(module: String, version: String? = ktorVersion): Any =
        "io.ktor:ktor-$module:$version"

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("application")
    id("com.bmuschko.docker-java-application")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(ktor("server-core")) // "io.ktor:ktor-server-core:$ktorVersion"
    implementation(ktor("server-netty")) // "io.ktor:ktor-ktor-server-netty:$ktorVersion"
    // Gson serialization
    // implementation(ktor("gson")) // "io.ktor:ktor-ktor-gson:$ktorVersion"
    // Kotlinx serialization
    // implementation(ktor("serialization")) // "io.ktor:ktor-ktor-serialization:$ktorVersion"
    // Jackson serialization
    implementation(ktor("jackson")) // "io.ktor:ktor-ktor-jackson:$ktorVersion"

    // logging if you want
    implementation("ch.qos.logback:logback-classic:1.2.5")

    implementation(project(":common"))
    implementation(project(":enotty-backend-stub"))
    implementation(project(":transport-open-api"))
    implementation(project(":transport-mapping-open-api"))
    implementation(project(":enotty-be-logic"))
    implementation(project(":enotty-be-service-openapi"))

    testImplementation(kotlin("test-junit"))
    testImplementation(ktor("server-test-host")) // "io.ktor:ktor-server-test-host:$ktorVersion"
}