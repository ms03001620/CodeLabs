package hackerrank

class CutTheSticks {
}

fun main() {
    println(arrayOf(8, 6, 4, 1).contentEquals(cutTheSticks(arrayOf(1, 2, 3, 4, 3, 3, 2, 1))))
}

fun cutTheSticks(arr: Array<Int>): Array<Int> {
    val result = ArrayList<Int>()
    var localArray: Array<Int>? = arr
    while(true){
        val cutResult = cut(localArray!!)
        result.add(cutResult.first)
        if(cutResult.second.isEmpty()){
            break
        }
        localArray = cutResult.second.toTypedArray()
    }
    return result.toTypedArray()
}

fun cut(arr: Array<Int>): Pair<Int, ArrayList<Int>> {
    val min = arr.min()
    val nextArray = ArrayList<Int>()
    var count = 0

    for (source in arr) {
        val stick = source - min
        if (stick > 0) {
            nextArray.add(stick)
        }
        count++
    }

    return Pair(count, nextArray)
}

/*
fun cutTheSticks(arr: Array<Int>): Array<Int> {
    val min = arr.min()
    val nextArray = ArrayList<Int>()
    var count = 0

    for (source in arr) {
        val stick = source - min
        if (stick > 0) {
            nextArray.add(stick)
        }
        count++
    }

    println(nextArray.toString())
    println(count)

    return emptyArray()
}*/
