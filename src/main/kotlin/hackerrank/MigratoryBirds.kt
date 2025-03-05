package hackerrank

class MigratoryBirds {
}

fun main() {

    println(migratoryBirds(arrayOf(1, 4, 4, 4, 5, 3)) == 4)
    println(migratoryBirds(arrayOf(1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4)) == 3)

    println(migratoryBirds(arrayOf(5,5,4,4,1)) == 4)
}

fun migratoryBirds(arr: Array<Int>): Int {
    val freqMap = linkedMapOf(
        1 to 0,
        2 to 0,
        3 to 0,
        4 to 0,
        5 to 0
    )

    var biggestKey = 1
    var biggestValue = 0

    arr.forEach {
        val freq = freqMap[it] ?: throw IllegalArgumentException("$it not found")
        val count = freq + 1
        freqMap[it] = count
        if (count > biggestValue) {
            biggestValue = count
            biggestKey = it
        } else if (count == biggestValue) {
            if (it < biggestKey) {
                biggestKey = it
            }
        }
    }

    return biggestKey
}