package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val state = MutableStateFlow(-1)

    launch {
        repeat(3){
            val value = it+1
            //println("repeat: $value")
            state.value = value
            delay(500)
        }
    }

    state.distinctUntilChanged{ a, b->
        println("comp $a, $b")
        a==b
    }.collect { value ->
        println("collect $value")
    }
}

fun main1() = runBlocking {
    val myFlow = flow {
        emit(1)
        delay(100)

        emit(1)
        delay(100)

        emit(2)
        delay(100)
    }

    println("\nFlow with distinctUntilChanged:")
    myFlow.distinctUntilChanged{ a, b->
        println("comp $a, $b")
        a==b
    }.collect { value ->
        println(value)
    }
}