package collections

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertArrayEquals
import java.util.Collections
import kotlin.test.assertEquals

class HR_case {
    @Test
    fun migratoryBirdsTest() {
        assertEquals(1, migratoryBirds(arrayOf(2, 2, 1, 1, 3)))
        assertEquals(1, migratoryBirds(arrayOf(1, 1, 2, 2, 3)))
        assertEquals(2, migratoryBirds(arrayOf(1, 2, 2, 3)))
        assertEquals(2, migratoryBirds(arrayOf(1, 1, 2, 2, 2, 3)))
    }

    fun migratoryBirds2(arr: Array<Int>): Int {

        return 0
    }

    fun migratoryBirds(arr: Array<Int>): Int {
        val scoreMap = mutableMapOf<Int, Int>()

        arr.forEach { bird ->
            scoreMap[bird] = scoreMap[bird]!! + 1
        }

        arr.forEach { index ->
            scoreMap[index]?.let {
                scoreMap[index] = it + 1
            } ?: run {
                scoreMap[index] = 1
            }
        }

        return scoreMap.toSortedMap().maxBy { it.value }.key
    }

    fun migratoryBirds1(arr: Array<Int>): Int {
        return arr.groupingBy { it }.eachCount().toSortedMap().maxBy { it.value }.key
    }

    @Test
    fun maximumPerimeterTriangleTest() {

        assertArrayEquals(arrayOf(-1) ,maximumPerimeterTriangle(arrayOf(1,2,3)))

        assertArrayEquals(arrayOf(2,3,4) ,maximumPerimeterTriangle(arrayOf(1,2,3,4)))
    }

    fun maximumPerimeterTriangle(sticks: Array<Int>): Array<Int> {
        val result = mutableListOf<Int>()





        return arrayOf(-1)
    }


}