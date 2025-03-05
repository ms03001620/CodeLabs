package hackerrank

fun main() {

    println(divisibleSumPairs(0, k = 5, ar = arrayOf(1,2,3,4,5,6))==3)

    println(divisibleSumPairs(0, k = 3, ar = arrayOf(1, 3, 2, 6, 1, 2)) == 5)
}

fun divisibleSumPairs(n: Int, k: Int, ar: Array<Int>): Int {
    var count = 0

    for (i in 0 until ar.size - 1) {
        for (j in i + 1 until ar.size) {

            val sum = ar[i] + ar[j]

            if (sum % k == 0) {
                count++
            }
        }
    }

    return count
}