package hackerrank

class JumpingOnTheCloudsRevisited {
}

fun main() {
    println(96 == jumpingOnClouds(arrayOf(0, 0, 1, 0), k = 2))
    println(92 == jumpingOnClouds(arrayOf(0, 0, 1, 0, 0, 1, 1, 0), k = 2))
    println(80 == jumpingOnClouds(arrayOf(1, 1 ,1 ,0, 1 ,1 ,0 ,0 ,0 ,0), k = 3))//1 1 1 0 1 1 0 0 0 0
}

fun jumpingOnClouds(c: Array<Int>, k: Int): Int {
    var pos = 0
    var eng = 100

    do {
        pos = (pos + k) % c.size
        eng -= 1 + c[pos] * 2
    } while (pos != 0)

    return eng
}


/*
fun jumpingOnClouds(c: Array<Int>, k: Int): Int {
    return 100 - toHitArray(c, k).map {
        if(c[it]==0){
            1
        }else{
            3
        }
    }.sum()
}

fun toHitArray(t: Array<Int>, step: Int): List<Int> {
    val result = ArrayList<Int>()
    var index = 0

    while (true) {
        val pIndex = index % t.size

        if (index != 0) {
            result.add(pIndex)
        }

        if (index > 0 && pIndex == 0) {
            break
        }
        index += step
    }
    return result
}
*/
