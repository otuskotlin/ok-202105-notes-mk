plugins{
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories{
    mavenCentral()
}

dependencies{
    implementation(kotlin("stdlib"))

    implementation(project(":enotty-be-common"))
    implementation(project(":transport-open-api"))

    testImplementation(kotlin("test"))
}