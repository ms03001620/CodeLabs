package hackerrank


class SockMerchant {
}

fun main() {

    println(2 == sockMerchant(0, arrayOf(1, 2, 1, 2, 1, 3, 2)))
    println(3 == sockMerchant(0, arrayOf(10, 20, 20, 10, 10, 30, 50, 10, 20)))


    val p =arrayOf(1, 2, 1, 2, 1, 3, 2).groupBy { it }
    println(p)
    println(p.map { it.value.size/2 })
}

fun sockMerchant(n: Int, ar: Array<Int>): Int {
    return ar.groupBy { it }.map { it.value.size / 2 }.sum()

}