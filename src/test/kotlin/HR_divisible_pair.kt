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


    @Test
    fun stringsXor() {
/*        val arr = mutableListOf<String>().also {
            it.add(readLine()!!)
            it.add(readLine()!!)
        }*/
        val args = arrayOf("10101", "00101")



        val result = StringBuilder()

        for(i in 0 until  args[0].length){
            val a = args[0][i]
            val b = args[1][i]
            val xorValue = a.toInt() xor b.toInt()
            result.append(xorValue)
        }
        println(result.toString())
    }

    @Test
    fun sockMerchantTest() {
        assertEquals(3, sockMerchant(2, arrayOf(10, 20, 20, 10, 10, 30, 50, 10, 20)))
/*        assertEquals(2, sockMerchant(2, arrayOf(1,1,3,4,7,5,5)))
        assertEquals(2, sockMerchant(2, arrayOf(1,1,3,4,2,5,5)))
        assertEquals(0, sockMerchant(0, arrayOf(1)))
        assertEquals(0, sockMerchant(0, arrayOf(1,2)))
        assertEquals(0, sockMerchant(0, arrayOf(1,2,3)))
        assertEquals(1, sockMerchant(0, arrayOf(1,2,2)))*/
    }

    fun sockMerchant(n: Int, ar: Array<Int>): Int {
        val cacheList = mutableListOf<Int>()
        var count = 0

        var index = 0

        while (index < ar.size) {
            val current = ar[index]
            if (cacheList.contains(current)) {
                cacheList.remove(current)
                count++
            } else {
                cacheList.add(current)
            }

            index++
        }
        return count
    }

    @Test
    fun mergeBirdTest() {
        assertEquals(1, migratoryBirds(arrayOf(1,1,2,2,3)))
        assertEquals(2, migratoryBirds(arrayOf(1,2,2,3)))
        assertEquals(3, migratoryBirds(arrayOf(1,2,3,4,5,4,3,2,1,3,4)))

    }

    fun migratoryBirds(arr: Array<Int>): Int {
        arr.sort()

        var current = -1;

        for (i in 0 until arr.size) {
            val element = arr[i]
            if (current == -1) {
                current = element
            } else {
                if (current == element) {
                    current = element
                    break
                }else{
                    current = element
                }
            }
        }

        return current
    }

    @Test
    fun mergeBirdTest1() {
        println(500/1000)

        val byteArray= byteArrayOf(99, 104, 97, 110, 103, 101, 32, 101, 114, 114, 111, 114)

        println(String(byteArray))

    }
}