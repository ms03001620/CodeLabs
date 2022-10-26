package collections

import org.junit.Test


class MapTest {

    @Test
    fun base() {
        val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)

        println( numbersMap.getOrElse("zero") { 0 })

    }
}