package flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.util.concurrent.atomic.AtomicInteger

data class Data(val value: Int)

object Api {
    private var data = Data(0)

    // Simulate data submission
    suspend fun submitData(newValue: Int) {
        delay(5000)
        data = Data(newValue)
        println("API: Data updated to: $newValue")
    }

    // Simulate data fetching
    suspend fun fetchData(): Data {
        delay(500) // Simulate network latency
        return data
    }
}

fun main() = runBlocking {
    launch {
        Api.submitData(20)
    }

    checkDataUpdated(20)
        .distinctUntilChanged()
        .catch {
            println("Error: $it")
        }
        .collect {
            println(it)
        }
}

fun checkDataUpdated(currentUpdateCount: Int) = flow {
    withTimeout(10 * 1000) {
        for(i in 1..100){
            delay(500L * i)
            val value = Api.fetchData().value
            println("checkDataUpdated: $value")
            if (currentUpdateCount == value) {
                emit(value)
                break
            }
        }
    }
}
