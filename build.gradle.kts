buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    }
}

plugins {
    id("com.diffplug.spotless") version "5.13.0"
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")
            ktlint("0.41.0")
        }

        kotlinGradle {
            target("*.gradle.kts")
            ktlint("0.41.0")
        }
    }
}
