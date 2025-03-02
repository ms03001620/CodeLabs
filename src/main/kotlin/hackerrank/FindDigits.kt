package hackerrank

class FindDigits {
}

fun main() {
    println(3 == findDigits(124))
    println(1 == findDigits(10))
    println(2 == findDigits(12))
    println(3 == findDigits(1012))
}

fun findDigits(n: Int): Int {
    return n.toString().map {
        it.digitToInt()
    }.filter {
        it != 0
    }.count {
        n % it == 0
    }
}