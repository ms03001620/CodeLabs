import kotlin.random.Random


val list = listOf<String>("A", "B", "C", "D", "E", "F", "K")


fun main() {
    var calcCount = 0

    while (true) {
        val target = getRandomList(3, list)
        if (checkPos(target)) {
            println("result:${target}")
            break
        } else {
            calcCount++
        }
    }

    println("calcCount:$calcCount")
}

fun checkPos(target: List<String>): Boolean {
    // case 1
    val res1 = postRight(target, hintList = listOf<String>("A", "B", "C"), 1, 1)
    // case 2
    val res2 = postRight(target, hintList = listOf<String>("A", "E", "F"), 1, 0)
    // case 3
    val res3 = postRight(target, hintList = listOf<String>("C", "K", "A"), 2, 0)
    // case 4
    val res4 = postRight(target, hintList = listOf<String>("D", "E", "B"), 0, 0)
    // case 5
    val res5 = postRight(target, hintList = listOf<String>("B", "D", "K"), 1, 0)
    return res1 && res2 && res3 && res4 && res5
}

fun postRight(
    target: List<String>,
    hintList: List<String>,
    okNumber:Int,
    okPos: Int
): Boolean {
    val numOk = containCount(target, hintList = hintList, okNumber)

    val posOk = sameCount(target, hintList) == okPos

    return numOk && posOk
}


fun sameCount(sourceList: List<String>, targetList: List<String>): Int {
    var pos = 0
    for (i in sourceList.indices) {
        if (sourceList[i] == targetList[i]) {
            pos++
        }
    }
    return pos
}

fun containCount(
    target: List<String>,
    hintList: List<String>,
    okCount: Int
): Boolean {
    var count = 0
    target.forEach {
        if (hintList.contains(it)) {
            count++
        }
    }
    return count == okCount
}

fun getRandomList(size: Int, all: List<String>): List<String> {
    val t = all.toMutableList()

    val result = mutableListOf<String>()
    for (i in 1..size) {
        result.add(getElement(t))
    }
    return result;
}

fun getElement(list: MutableList<String>): String {
    val randomIndex = Random.nextInt(list.size)
    return list.removeAt(randomIndex)
}