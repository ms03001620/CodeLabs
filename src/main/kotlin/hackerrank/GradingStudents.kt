package hackerrank

import java.util.*


fun main() {

    arrayOf(73, 67, 38, 33).let {
        gradingStudents(it)
    }.let {
        arrayOf(75,67,40, 33).contentEquals(it)
        //arrayOf(73, 67, 38, 33).contentEquals(it)
    }.let {
        println(it)
    }
}

fun gradingStudents(grades: Array<Int>): Array<Int> {
    return grades.map {
        upGrade(it)
    }.toTypedArray()
}

fun upGrade(originGrade: Int): Int {
    if (originGrade < 38) {
        return originGrade
    }

    val five = 5

    val remainder = originGrade % five
    if (remainder == 0) {
        return originGrade
    } else {
        val now = originGrade / five
        val next = now + 1

        if ((next * five - originGrade) < 3) {
            return next * five
        } else {
            return originGrade
        }
    }
}
