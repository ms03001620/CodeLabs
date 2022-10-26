package collections

import org.junit.Test
import java.lang.StringBuilder
import java.lang.UnsupportedOperationException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HR_divisible_pair {

    fun divisibleSumPairs(n: Int, k: Int, ar: Array<Int>): Int {
        // Write your code here
        makePairList(ar).let {
            calcValidPair(it, k)
        }.let {
            return it
        }
    }

    fun makePairList(ar: Array<Int>): MutableList<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        // 1,2,3,4,5
        // i, j
        // ------i,j max

        var size = ar.size
        var i = 0
        val iMax = size - 2
        val jMax = size - 1

        while (i <= iMax) {
            var j=i+1
            val first = ar[i]
            while (j <= jMax) {
                val second = ar[j]
                result.add(Pair(first, second))
                j++
            }
            i++
        }
        return result
    }

    fun calcValidPair(pair: List<Pair<Int, Int>>, k: Int): Int {
        return pair.count {
            canDivisible(it.first + it.second, k)
        }
    }

    fun canDivisible(sum: Int, k: Int) = sum % k == 0



    @Test
    fun calcValidPair() {
        val list = mutableListOf<Pair<Int, Int>>()

        assertEquals(0, calcValidPair(list, 5))

        list.add(Pair(0, 5))
        list.add(Pair(2, 3))
        list.add(Pair(1, 4))
        list.add(Pair(7, 9))

        assertEquals(3, calcValidPair(list, 5))
    }


    @Test
    fun canDivisibleTest() {
        assertTrue(canDivisible(5, 5))
        assertTrue(canDivisible(15, 5))
    }


    @Test
    fun componentTest() {
        assertEquals(3, divisibleSumPairs(n = 6, k = 5, ar = arrayOf(1, 2, 3, 4, 5, 6)))
        assertEquals(5, divisibleSumPairs(n = 6, k = 3, ar = arrayOf(1, 3, 2, 6, 1, 2)))
    }
}