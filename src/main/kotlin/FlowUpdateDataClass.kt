import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow


fun main() = runBlocking {
    println("start main")

    val sharedFlow = MutableSharedFlow<Person>(replay = 1)


    GlobalScope.launch {
        sharedFlow.collectLatest {
            println("P:$it")
        }
    }

    println("start fetch")
    fetchPerson().collectLatest {
        sharedFlow.emit(it)
    }

    GlobalScope.launch {
        delay(5000)
        println("start update")
        val oldPerson = sharedFlow.replayCache.last()

        sharedFlow.emit(oldPerson.copy(address = oldPerson.address.copy(post = 200)))
    }

    delay(20* 1000)
}

fun fetchPerson() = flow<Person>{
    delay(3500)

    val person = Person(name = "mark", age = 30, address = Address(path = "xxxxxx", post = 100))
    emit(person)
}


data class Address(val path: String, val post: Int)
data class Person(val name: String, val age: Int, val address: Address)