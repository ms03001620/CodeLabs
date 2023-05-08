class TakeWhile {


}


fun main() {
/*    val numbers = intArrayOf(1, 2, 3, 4, 5, 7, 20, 10, 30, 0)

    val p = numbers.takeWhile {
        it < 5
    }

   // println(p.size)

    p.forEach {
        println(it)
    }*/

    dropWhile();
}

private fun dropWhile() {
    //从头开始找不满足的开始取值，满足的抛弃头部
    val mList = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    val resultList = mList.dropWhile { it <= 3 }
    println(resultList)
}
