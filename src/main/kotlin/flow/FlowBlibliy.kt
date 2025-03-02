package flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

// https://www.bilibili.com/video/BV1df4y1f7kV/?spm_id_from=333.337.search-card.all.click&vd_source=1dad961ab249a419b98e5252bbdd5127
// 社区说｜Kotlin Flow 的原理与设计哲学
fun main() {
    val scope  = CoroutineScope(Job())

    scope.launch {
        flow{
            logX("start")
            emit(1)
            emit(2)
            emit(3)
            emit(4)
            emit(5)
        }/*.collect{
            logX(it)
        }*/
    }

    Thread.sleep(1000)

}

fun logX(log: Any?){
    println(log)
}