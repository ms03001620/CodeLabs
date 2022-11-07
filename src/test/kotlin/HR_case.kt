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

        assertArrayEquals(arrayOf(1,1,1) ,maximumPerimeterTriangle(arrayOf(1,1,1)))

        assertArrayEquals(arrayOf(1,3,3) ,maximumPerimeterTriangle(arrayOf(1,3,3)))

        assertArrayEquals(arrayOf(2,3,4) ,maximumPerimeterTriangle(arrayOf(1,2,3,4)))

        assertArrayEquals(arrayOf(3,4,5) ,maximumPerimeterTriangle(arrayOf(1,2,3,4,5,10)))
    }

    fun maximumPerimeterTriangle1(sticks: Array<Int>): Array<Int> {
        sticks.sorted().toMutableList().windowed(3, 1, false).filter {
            //println(it.toList())
            val a = it[0]
            val b = it[1]
            val c = it[2]
            val ab_c = a + b > c
            val ac_b = a + c > b
            val bc_a = b + c > a
            ab_c && ac_b && bc_a
        }.let {
            //println("ok size:${it.size}")

            //it.forEach {
            //    println(it.toList())
            //}

            if (it.isNotEmpty()) {
                it.maxBy {
                    it.sum()
                }.let {
                    //println("ok")
                    //println(it.toList())
                    return it!!.toTypedArray()
                }
            }
        }

        return arrayOf(-1)

    }

    fun maximumPerimeterTriangle(sticks: Array<Int>): Array<Int> {
        sticks.sortDescending()
        for (a in 0 until sticks.size - 2) {
            val b = a + 1
            val c = a + 2
            if (sticks[a] < sticks[b] + sticks[c]) {
                return arrayOf(sticks[c], sticks[b], sticks[a])
            }
        }
        return arrayOf(-1)
    }

    @Test
    fun findZigZagSequenceTest() {
        assertArrayEquals(arrayOf(1,3,2) ,findZigZagSequence(arrayOf(1,2,3)))
        assertArrayEquals(arrayOf(1,2,3,7,6,5,4) ,findZigZagSequence(arrayOf(1,2,3,4,5,6,7)))
    }

    fun findZigZagSequence(arr: Array<Int>): Array<Int> {
        // 1,2,3,4,5  to 5,4,3,2,1 , target = 12543
        val list = arr.sortedDescending().toMutableList()
        var moveStep = (list.size - 1) / 2
        val movedList = mutableListOf<Int>()
        while (moveStep > 0) {
            val last = list.removeLast()
            movedList.add(last)
            moveStep--
        }
        list.addAll(0, movedList)
        return list.toTypedArray()
    }

    @Test
    fun pageCountTest(){
        assertEquals(1, pageCount(6, 2))
        assertEquals(0, pageCount(5, 4))
    }


    fun pageCount(n: Int, p: Int): Int {
        //https://www.hackerrank.com/challenges/three-month-preparation-kit-drawing-book/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=three-month-preparation-kit&playlist_slugs%5B%5D=three-month-week-three
        val list = mutableListOf<Pair<Int, Int>>()
        var index = 0;

        while(index<= n){
            list.add(Pair(index, (index+1)))
            index=index+2
        }

        val target = if(p%2==0) Pair(p, p+1) else Pair(p-1, p)

        val f = list.indexOf(target)
        val b = list.lastIndex - list.lastIndexOf(target)

        return Math.min(f, b)
    }



}