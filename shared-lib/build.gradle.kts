plugins {
    kotlin("multiplatform") version Versions.kotlin
    kotlin("plugin.serialization") version Versions.kotlin

}

kotlin {
    jvm() {
        compilations["main"].kotlinOptions {
            jvmTarget = Versions.jvm
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-Xuse-experimental=kotlin.Experimental"
            )
        }
    }
    js()
//    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.serialization}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation(kotlin("reflect"))
                implementation("com.github.kittinunf.fuel:fuel:${Versions.fuel}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}")
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
//                implementation("org.jetbrains.kotlinx:kotlinx-html-js:${Versions.kotlinxHtml}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.serialization}")
            }
        }
        js().compilations["test"].defaultSourceSet { /* ... */ }
    }
}
