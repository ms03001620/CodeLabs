package hackerrank

class UtopianTree {

}

fun main() {
    println(1 == utopianTree(0))
    println(2 == utopianTree(1))
    println(3 == utopianTree(2))
    println(6 == utopianTree(3))
    println(7 == utopianTree(4))
    println(14 == utopianTree(5))
}

fun utopianTree(n: Int): Int {
    var h = 0
    for (i in 0..n) {
        if (i == 0) {
            h = 1
            continue
        }

        if (i % 2 != 0) {
            h = h * 2
        } else {
            h = h + 1
        }
    }
    return h
}