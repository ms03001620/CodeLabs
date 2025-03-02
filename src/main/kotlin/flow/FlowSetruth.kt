package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.random.Random


fun main() = runBlocking {
    useFlow()
}

suspend fun useFlow() {
    val flow = flow {
        repeat(times = 10) {
            emit(request().also {
                println("gen:$it")
            })
        }
    }

    flow.map { it * it }
        .filter { it % 2 == 0 }
        .take(count = 2)
        .onEach { println(it) }
        .collect()

    println("end")
}

suspend fun request(): Int {
    delay(Random.nextLong(until = 1000))
    return Random.nextInt(until = 100)
}