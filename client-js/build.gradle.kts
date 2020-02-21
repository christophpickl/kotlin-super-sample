// in order to access the output, run the following command and open the browser at: http://localhost:8080
// ./gradlew run --continuous

plugins {
    kotlin("js") version Versions.kotlin
}

dependencies {
    implementation(kotlin("stdlib-js"))
    testImplementation(kotlin("test-js"))
}

kotlin {
    target {
        // useCommonJs()
        // nodejs()
        browser {

        }
    }
//    sourceSets["main"].dependencies {
//        implementation(npm("react", "16.12.0"))
//    }
}

