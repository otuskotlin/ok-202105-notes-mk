rootProject.name = "Enotty"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
    }
}
include("common")
