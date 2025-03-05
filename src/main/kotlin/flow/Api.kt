package flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

data class Data(val value: Int)

object Api {
    private var data = Data(0)
    private val updateCount = AtomicInteger(0)

    // Simulate data submission
    suspend fun submitData(newValue: Int) {
        println("API: Submitting data: $newValue")
        updateCount.incrementAndGet()
        // Simulate a successful submission immediately
        println("API: Data submitted successfully")
        // Simulate data update delay (5 seconds)
        delay(5000)
        data = Data(newValue)
        println("API: Data updated to: $newValue")
    }

    // Simulate data fetching
    suspend fun fetchData(): Data {
        delay(500) // Simulate network latency
        return data
    }

    fun getUpdateCount(): Int {
        return updateCount.get()
    }
}

fun main() = runBlocking {
    launch {
        Api.submitData(20)
    }

    // Start collecting data after submission
    checkDataUpdated(20)
        .distinctUntilChanged()
        .collect {
        println(it)
    }

}

 fun checkDataUpdated(currentUpdateCount: Int)= flow {
        while (true) {
            val api = Api.fetchData().value
            emit(api)
            delay(500) // Fetch data every 1 second

            if(currentUpdateCount == api){
                break
            }
        }
    }
