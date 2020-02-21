import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
}

subprojects {

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

        withType<Test> {
            testLogging {
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
//            showStandardStreams = true // enable for more details
            }
            useTestNG {
                parallel = "classes"
                threadCount = 5
            }
        }

    }

}
