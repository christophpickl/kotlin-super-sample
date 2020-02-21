import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
}

subprojects {
    group = "com.github.christophpickl.kss"
    version = "1.0"

    repositories {
        mavenCentral()
        jcenter()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = Versions.jvm
                freeCompilerArgs = listOf(
                    "-Xjsr305=strict",
                    "-Xuse-experimental=io.ktor.util.KtorExperimentalAPI",
                    "-Xuse-experimental=kotlin.contracts.ExperimentalContracts"
                )
            }
        }
    }

}
