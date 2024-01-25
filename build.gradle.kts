plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.diffplug.spotless") version "6.25.0"
}

subprojects {

    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            ktlint("1.0.0")
        }

        kotlinGradle {
            target("*.gradle.kts")
            ktlint("1.0.0")
        }
    }
}