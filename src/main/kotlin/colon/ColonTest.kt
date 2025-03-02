package flow

fun main() {
    testCallback(::testHello)

    val t = A::fn
    t(A())
}

fun testHello() {
    println("hello")
}

fun testCallback(callback: () -> Unit) {
    callback()
}

class A {
    fun fn() {
        println("a")
    }
}