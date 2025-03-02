package hackerrank

class AngryProfessor {
}

fun main() {
    println(angryProfessor(3, arrayOf(-1,-3,4,2)))
    println(angryProfessor(2, arrayOf(0,-1,2,1)))
}

fun angryProfessor(k: Int, a: Array<Int>): String {
    return if (a.count { it <= 0 } < k) "YES" else "NO"
}
