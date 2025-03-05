package hackerrank

import java.util.*

class PermutationEquation {
}

fun main() {
    println(Arrays.equals(arrayOf(4, 2, 5, 1, 3), permutationEquation(arrayOf(5, 2, 1, 3, 4))))
    println(Arrays.equals(arrayOf(2, 3, 1), permutationEquation(arrayOf(2, 3, 1))))
    println(Arrays.equals(arrayOf(1, 3, 5, 4, 2), permutationEquation(arrayOf(4, 3, 5, 1, 2))))
}

fun permutationEquation(p: Array<Int>): Array<Int> {
    return (1..p.size).map {
        indexToNumber(p.indexOf(it))
    }.map {
        indexToNumber(p.indexOf(it))
    }.toTypedArray()
}

fun indexToNumber(index: Int) = index + 1