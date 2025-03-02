package hackerrank

class TheHurdleRace {
}

fun main() {
    println(2 == hurdleRace(1, arrayOf(1,2,3,3,2)))
    println(2 == hurdleRace(4, arrayOf(1,6,3,5,2)))
    println(0 == hurdleRace(7, arrayOf(2,5,4,5,2)))
}

fun hurdleRace(k: Int, height: Array<Int>): Int {
    return Math.max(height.max() - k, 0)
}