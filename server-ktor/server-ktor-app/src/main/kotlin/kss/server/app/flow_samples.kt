package kss.server.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// see: https://www.youtube.com/watch?v=tYcqn48SMT8

@ExperimentalCoroutinesApi
fun main() {
    println("START")
    val used = measureTimeMillis {
//        `basic sample`()
        `sample for UIs to unblock UI thread`()
    }
    println("END ${used}ms")
}
// ================================================================================

fun `basic sample`() = runBlocking {
    `flow sample using buffered to go parallel computation`() // 2.6 sec
//            `flow sample using single coroutine leading to asynchronous sequential computation`() // 4.0 sec
}

private val list = listOf("A", "B", "C", "D").iterator()

private val flow = flow {
    println("pre-emit")
    while (list.hasNext()) {
        println("delay and emit")
        delay(500)
        emit(list.next())
    }
    println("post-emit")
}


suspend fun `flow sample using single coroutine leading to asynchronous sequential computation`() {
    flow.collect(::collector)
}

@ExperimentalCoroutinesApi
suspend fun `flow sample using buffered to go parallel computation`() {
    flow.buffer().collect(::collector)
}

suspend fun collector(it: String) {
    delay(500)
    println("collected: $it")
}

// ================================================================================

@ExperimentalCoroutinesApi
fun `sample for UIs to unblock UI thread`() = runBlocking {
    val flow = createFlow()
    flow.collect { println(it) }
}

@ExperimentalCoroutinesApi
fun createFlow(): Flow<String> = flowOf("A", "B", "C").map {
        compute(it)
    }
    .flowOn(Dispatchers.Default) // context preservation

suspend fun compute(it: String): String {
    delay(500)
    return "$it-2"
}
