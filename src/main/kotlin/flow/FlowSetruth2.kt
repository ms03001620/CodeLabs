package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.random.Random


fun main() = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>(
        extraBufferCapacity = 1
    )


/*    sharedFlow
        .onEach { println(it) }
        .launchIn(this)*/

    delay(1000)

    repeat(times = 10) {
        println("send: $it")
        sharedFlow.emit(it)
    }


    println("end")
}

