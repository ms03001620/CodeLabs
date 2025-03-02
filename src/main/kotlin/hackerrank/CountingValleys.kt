package hackerrank

class CountingValleys {
}

fun main() {
    println(1 == countingValleys(0, "DDUUUUDD"))
    println(1 == countingValleys(0, "UDDDUDUU"))
}

fun countingValleys(steps: Int, path: String): Int {
    val sea = 0
    var startElevation = 0
    var isIn = false
    var countIn = 0
    var countOut = 0

    path.forEach {
        when (it) {
            'D' -> startElevation -= 1
            'U' -> startElevation += 1
            else -> throw UnsupportedOperationException("char:${it}")
        }
        if (startElevation < sea && !isIn) {
            isIn = true
            countIn++
        }
        if (startElevation >= sea && isIn) {
            isIn = false
            countOut++
        }
    }

    return countOut
}