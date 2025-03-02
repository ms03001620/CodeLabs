package hackerrank

class BreakingBestAndWorstRecords {
}

fun main() {

    println(breakingRecords(arrayOf(12, 24, 10, 24)).contentEquals(arrayOf(1, 1)))

    println(breakingRecords(arrayOf(10, 5, 20, 20, 4, 5, 2, 25, 1)).contentEquals(arrayOf(2, 4)))
}


fun breakingRecords(scores: Array<Int>): Array<Int> {
    var highest = scores.first()
    var lowest = scores.first()

    var highCount = 0
    var lowCount = 0

    for (i in scores) {
        if (i > highest) {
            highCount++
            highest = i
        } else if (i < lowest) {
            lowCount++
            lowest = i
        } else {
            // ignore
        }
    }

    return arrayOf(highCount, lowCount)
}
