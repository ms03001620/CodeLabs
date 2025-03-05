package hackerrank

class TheBirthdayDar {
}

fun main() {
    println(birthday(arrayOf(2, 2, 1, 3, 2), d = 4, m = 2) == 2)
    println(birthday(arrayOf(1, 2, 1, 3, 2), d = 3, m = 2) == 2)
    println(birthday(arrayOf(1, 1, 1, 1, 1, 1), d = 3, m = 2) == 0)
    println(birthday(arrayOf(4), d = 4, m = 1) == 1)
}

fun birthday(s: Array<Int>, d: Int, m: Int): Int {
    return s.asSequence().windowed(size = m, step = 1).count {
        it.sum() == d
    }
}