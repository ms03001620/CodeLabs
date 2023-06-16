import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class InstantTest {

}

fun main() = runBlocking {
/*    flowOf (1,2,3,4,5).onStart {
        println("onStart")
    }.onEmpty {
        println("empty")
    }.onEach {
        print(".")
        if (it == 3) {
            throw IllegalAccessException("error:$it")
        }

    }.catch {
        println("catch: $it")
    }.onCompletion {
        println("onCompletion :$it")
    }.collect()*/

    println("0.01".toFloat())
}