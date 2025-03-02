package hackerrank

class BeautifulDaysAtTheMovies {
}

fun main() {
    println(2==beautifulDays(20, 23,6))

   // println(20.toString().reversed().toInt())

}

fun beautifulDays(i: Int, j: Int, k: Int): Int {
    return (i..j).map {
        Math.abs(it - it.toString().reversed().toInt())
    }.count {
        it % k ==0
    }
}
