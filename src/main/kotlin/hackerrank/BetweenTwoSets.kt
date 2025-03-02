package hackerrank

class BetweenTwoSets {
}

fun main() {
    val a = arrayOf(2, 6)
    val b = arrayOf(24, 36)

    println(getTotalX(a, b))
}

fun getTotalX(a: Array<Int>, b: Array<Int>): Int {
    val start = a.last()// a.max()
    val end = b.first()// b.min()

    return (start..end).count {
        isListDiv(it, a) && isNumberDiv(it, b)
    }
}

fun isListDiv(num: Int, a: Array<Int>): Boolean {
    for (i in a) {
        if (num % i != 0) {
            return false
        }
    }
    return true
}

fun isNumberDiv(num: Int, b: Array<Int>): Boolean {
    for (i in b) {
        if (i % num != 0) {
            return false
        }
    }
    return true
}