package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun isEventHappening(probability: Float): Boolean {
    require(probability in 0.0..1.0) { "Probability must be between 0 and 1" }
    return Random.nextFloat() < probability
}

suspend fun fetchData(): String {
    delay(500)
    if (isEventHappening(.8f)) {
        throw Exception("请求失败")
    }
    return "成功"
}

fun requestWithRetry(): Flow<String> {
    return flow {
        emit(fetchData())
    }.retryWhen { cause, num ->
        println("请求失败，重试次数:$num， e:${cause.message}")
        num < 2
    }.catch {
        emit("最终失败")
    }
}

fun main() = runBlocking {
    requestWithRetry().collect {
        println(it)
    }
}