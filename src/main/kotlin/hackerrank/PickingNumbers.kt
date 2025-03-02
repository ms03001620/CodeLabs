package hackerrank

class PickingNumbers {
}

fun main() {

    //println(4 == pickingNumbers(arrayOf(1, 1, 2, 2, 4, 4, 5, 5, 5)))

   // println(5 == pickingNumbers(arrayOf(1,2,2,3,1,2)))

    println(3 == pickingNumbers(arrayOf(4,6,5,3,3,1)))

}

fun pickingNumbers(a: Array<Int>): Int {

    a.forEachIndexed { index, i ->
        print("${calcAbsLeftRight(index, a)} ")
    }


    return 0
}

fun calcAbsLeftRight(index: Int, a: Array<Int>): Int {
    if (a.getOrNull(index) == null || a.size==1) {
        throw IllegalArgumentException("index err: $index, a.size:${a.size}")
    }

    if (index == 0) {
        return Math.abs(a[index] - a[index + 1])
    }

    if (index == a.size - 1) {
        return Math.abs(a[index] - a[index - 1])
    }

    val aLeft = Math.abs(a[index] - a[index - 1])
    val aRight = Math.abs(a[index] - a[index + 1])

    return Math.max(aLeft, aRight)
}