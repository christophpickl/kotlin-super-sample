dependencies {
    implementation(project(":server-ktor:server-ktor-domain"))
    implementation(project(":shared-lib"))

    // ktor
    fun ktor(suffix: String = "") = "io.ktor:ktor$suffix:${Versions.ktor}"
    implementation(ktor())
    implementation(ktor("-server-netty"))
    implementation(ktor("-jackson"))

    // kodein
    implementation("org.kodein.di:kodein-di-generic-jvm:${Versions.kodein}")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:${Versions.kodein}")

    // logging
    implementation("io.github.microutils:kotlin-logging:${Versions.klogging}")
    implementation("ch.qos.logback:logback-classic:${Versions.logback}")

    testImplementation(ktor("-server-test-host")) {
        exclude(group = "junit", module = "junit")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-test-junit")
    }
    testImplementation("org.testng:testng:${Versions.testng}")
    testImplementation("org.skyscreamer:jsonassert:${Versions.jsonassert}")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:${Versions.assertk}")
//    testImplementation("io.mockk:mockk:${Versions.mockk}")
//    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
//    testImplementation("org.kodein.di:kodein-di-generic-jvm:${Versions.kodein}")
//    testImplementation("com.jayway.jsonpath:json-path:${Versions.jsonPath}")
//    testImplementation("com.github.tomakehurst:wiremock-jre8:${Versions.wiremock}")
}
