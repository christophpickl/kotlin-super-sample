package kss.server.app

import kss.server.api.ClassRto

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        println("app server: " + ClassRto("any-id", "Kotlin 101"))
    }
}
