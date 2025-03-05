package hackerrank

class BonAppetit {
}

fun main() {
    println(getBonAppetitInfo(bill = arrayOf(3, 10, 2, 9), k = 1, b = 12) == "5")
    println(getBonAppetitInfo(bill = arrayOf(3, 10, 2, 9), k = 1, b = 7) == "Bon Appetit")
}

fun bonAppetit(bill: Array<Int>, k: Int, b: Int): Unit {
    println(getBonAppetitInfo(bill, k, b))
}

fun getBonAppetitInfo(bill: Array<Int>, k: Int, b: Int): String {
    val actual = bill.filterIndexed { index, _ ->
        index != k
    }.sum() / 2

    return if (actual == b) {
        "Bon Appetit"
    } else {
        (b - actual).toString()
    }
}