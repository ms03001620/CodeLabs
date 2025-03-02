package coro

import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.measureTimeMillis

class TestLearn {
}

val formatTime = SimpleDateFormat("HH:mm:ss SSS", Locale.CHINA)
fun log(msg: String) = println("${formatTime.format(System.currentTimeMillis())} [${Thread.currentThread().name}] $msg")


fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // 执⾏⼀些计算
        // 启动第⼀个
        one.start()
        // 启动第⼆个
        two.start()
        log("The answer is ${one.await() + two.await()}")
    }
    log("Completed in $time ms")
}



fun main1() {
    GlobalScope.launch {
        // delay 是一个特殊的挂起函数 ，它不会造成线程阻塞，但是会挂起协程，并且只能在协程中使用
        delay(1000L)
        log("World!")
        delay(5000L)
        log("End") // 这个不会执行
    }

    log("Hello,")
    // 协程已在等待时主线程还在继续，阻塞主线程2秒钟来保证JVM存活
    Thread.sleep(8000L)


}

suspend fun doSomethingUsefulOne(): Int {
    println("doSomethingUsefulOne")
    delay(1000L)
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L)
    return 29
}

