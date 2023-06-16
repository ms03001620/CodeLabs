import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import java.lang.Math.log
import java.lang.System.currentTimeMillis
import kotlin.system.measureTimeMillis

class Tst {
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun foo(): Flow<String> = flow {
    for (i in 1..5) {
        emit("Emitting $i")
    }
}

fun main() = runBlocking<Unit> {
    (1..5).asFlow().onEach {
        //if (it == 3) throw IllegalStateException("a")
    }.catch {

    }.collect {
            println("collect :$it")
        }
}