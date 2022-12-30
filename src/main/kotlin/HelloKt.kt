import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/*fun main(args: Array<String>) {
    println("Hello, World!")
}*/

fun main() = runBlocking{

/*
    flow {
        emit(7)
        emit(0)
        emit(1) //从此项开始不满足条件
        emit(2)
        emit(3)
        emit(4)
        emit(5)
        emit(6)
    }.filter { it % 2 == 0 }.collect { //0将被丢弃
        println(it)
    }
*/


    val t = runTaskWithSuspend()
    println(t)

}

interface SingleMethodCallback {
    fun onCallBack(value: String)
}

private fun runTask(callback: SingleMethodCallback) {

    Thread({
        callback.onCallBack("result1")
    }).start()

    Thread({
        callback.onCallBack("result2")
    }).start()
    Thread({
        callback.onCallBack("result3")
    }).start()
    Thread({
        callback.onCallBack("result4")
    }).start()
}


suspend fun runTaskWithSuspend(): String {
    // suspendCoroutine是一个挂起函数
    return suspendCoroutine { continuation ->
        runTask(object : SingleMethodCallback {
            val atomicBoolean = AtomicBoolean()

            override fun onCallBack(value: String) {
                Thread.sleep(20)
                if(canResume()){
                    Thread.sleep(10)
                    continuation.resume(value)
                }
            }

            fun canResume(): Boolean {
                return atomicBoolean.compareAndSet(false, true)
            }
        })


    }
}
