package hackerrank

import kotlin.math.abs

class Kangaroo {
}

fun main() {

    println("YES" == kangaroo(2, 1, 1, 2))
    println("YES" == kangaroo(0, 3, 4, 2))
    println("NO" == kangaroo(0, 2, 5, 3))
}



fun kangaroo(x1: Int, v1: Int, x2: Int, v2: Int): String {
    var aX = x1
    var bX = x2

    val lastDesc = calcDis(aX, bX)

    for (i in 0..Integer.MAX_VALUE) {
        aX += v1
        bX += v2

        val nowDesc = calcDis(aX, bX)

        if (nowDesc >= lastDesc) {
            return "NO"
        }

        if (nowDesc == 0) {
            return "YES"
        }
    }
    return "NONE"
}

fun calcDis(aX: Int, bX: Int) = abs(aX - bX)


/*
fun kangaroo(x1: Int, v1: Int, x2: Int, v2: Int): String {
    return when {
        x1 == x2 -> "YES"
        x1 > x2 || v1 <= v2 -> "NO"
        else -> kangaroo(x1 + v1, v1, x2 + v2, v2)
    }
}

fun main(args: Array<String>) {
    val `in` = Scanner(System.`in`)
    val x1 = `in`.nextInt()
    val v1 = `in`.nextInt()
    val x2 = `in`.nextInt()
    val v2 = `in`.nextInt()
    val result = kangaroo(x1, v1, x2, v2)
    println(result)
}*/
