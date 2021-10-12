plugins{
    kotlin("jvm")
}

dependencies{
    implementation(kotlin("stdlib"))

    implementation(project(":common"))
    implementation(project(":enotty-backend-stub"))
    implementation(project(":transport-open-api"))
    implementation(project(":transport-mapping-open-api"))
    implementation(project(":enotty-be-logic"))
}