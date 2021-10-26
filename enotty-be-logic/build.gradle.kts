plugins {
    kotlin("jvm")
}

dependencies {
    val coroutinesVersion: String by project

    implementation(kotlin("stdlib"))

    implementation(project(":enotty-be-common"))
    implementation(project(":enotty-mp-common-cor"))
    implementation(project(":enotty-backend-stub"))
    implementation(project(":enotty-be-common-validation"))

    implementation(kotlin("test"))
    implementation(kotlin("test-junit"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

}