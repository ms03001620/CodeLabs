package io.netty.example


interface Req<T> {
    fun product(): T
    fun consume(t: T)
}


abstract class Request<T>() : Runnable, Req<T> {
    override fun run() {
        consume(product())
    }

    abstract override fun product(): T

    abstract override fun consume(t: T)
}

class RequestA() : Request<String>() {
    override fun product(): String {
        return ""
    }

    override fun consume(t: String) {
    }
}

class RequestB() : Request<Int>() {
    override fun product(): Int {
        return 0
    }

    override fun consume(t: Int) {
    }
}

val hashMap = HashMap<Int, Runnable>()
val requestD = RequestD(hashMap)

fun main() {
    requestD.post(RequestA())
    requestD.post(RequestB())
}


class RequestD(private val map: HashMap<Int, Runnable>) {
    fun decode(id: Int) {
        val p = map[id]?.run()
    }

    fun post(data: Runnable) {
        map[100] = data
    }
}
