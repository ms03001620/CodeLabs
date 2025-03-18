package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() = runBlocking{
    ffffff(
        7,
        submitFlow = submitFlow(),
        fetchFlow().map {
            it.first
        }
    ).collect {
        println(it)
    }
}

fun ffffff(result: Int,
           submitFlow: Flow<Any>,
           fetchFlow: Flow<Any>

) = flow {
    submitFlow.collect{
        println("submitFlow:$it")
        if (false) {
            emit(false)
        }else{
            while(true){
                val value = fetchFlow.first()
                println(value)
                if(value == result){
                    emit(true)
                    break
                }
            }
        }
    }
}


fun submitFlow() = flow {
    delay(1000)
    emit(Random.nextBoolean())
}

fun fetchFlow() = flow {
    delay(1000)
    emit(Pair(Random.nextInt(10),true))
}


/*fun main() = runBlocking {
    // 模拟 c 的初始化过程
    val cFlow = flow {
        println("Initializing c...")
        delay(1000) // 模拟初始化耗时
        emit("Result from c")
    }.shareIn(this, SharingStarted.Lazily, replay = 1) // 使用 SharedFlow 共享结果

    // a 和 b 的流
    val aFlow = flow {
        println("a is waiting for c...")
        val cResult = cFlow.first() // 等待 c 的结果
        emit("a received: $cResult")
    }

    val bFlow = flow {
        println("b is waiting for c...")
        val cResult = cFlow.first() // 等待 c 的结果
        emit("b received: $cResult")
    }

    // 同时收集 a 和 b
    launch {
        aFlow.collect { println(it) }
    }

    launch {
        bFlow.collect { println(it) }
    }

    delay(10000)
}*/



