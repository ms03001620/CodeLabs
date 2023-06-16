import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PosOkKtTest {

    @Test
    fun sameCountTest() {
        val source = listOf<String>("A", "B", "C")
        assertEquals(3, sameCount(source, targetList = listOf<String>("A", "B", "C")))

        assertEquals(1, sameCount(source, targetList = listOf<String>("X", "B", "Z")))

        assertEquals(1, sameCount(source, targetList = listOf<String>("A", "Y", "X")))

        assertEquals(0, sameCount(source, targetList = listOf<String>("C", "Y", "X")))

        assertEquals(0, sameCount(source, targetList = listOf<String>("C", "Y", "A")))

        assertEquals(0, sameCount(source, targetList = listOf<String>("X", "Y", "Z")))
    }


    @Test
    fun case1Test() {
        val error = listOf<String>("A", "B", "C")

        assertTrue(case1(target = listOf("A", "Y", "Z"), errorList = error))
        assertTrue(case1(target = listOf("X", "B", "Z"), errorList = error))
        assertTrue(case1(target = listOf("X", "Y", "C"), errorList = error))

        assertFalse(case1(target = listOf("A", "B", "Z"), errorList = error))
        assertFalse(case1(target = listOf("X", "B", "A"), errorList = error))
        assertFalse(case1(target = listOf("A", "Y", "C"), errorList = error))
        assertFalse(case1(target = listOf("A", "B", "C"), errorList = error))

        assertFalse(case1(target = listOf("X", "Y", "Z"), errorList = error))
    }

    @Test
    fun case2Test() {
        val error = listOf<String>("A", "B", "C")

        assertTrue(case2(target = listOf("A", "B", "Z"), errorList = error))
        assertTrue(case2(target = listOf("X", "A", "B"), errorList = error))
        assertTrue(case2(target = listOf("A", "Y", "B"), errorList = error))

        assertFalse(case2(target = listOf("A", "B", "C"), errorList = error))
        assertFalse(case2(target = listOf("X", "Y", "Z"), errorList = error))
    }


    @Test
    fun case4Test() {
        val error = listOf<String>("A", "B", "C")

        assertFalse(case4(target = listOf("A", "B", "C"), errorList = error))
        assertFalse(case4(target = listOf("B", "4", "6"), errorList = error))
        assertFalse(case4(target = listOf("C", "1", "4"), errorList = error))
        assertFalse(case4(target = listOf("1", "X", "A"), errorList = error))

        assertTrue(case4(target = listOf("D", "E", "F"), errorList = error))
        assertTrue(case4(target = listOf("1", "2", "3"), errorList = error))
    }

    fun case1(
        target: List<String>,
        errorList: List<String>
    ): Boolean {
        return containCount(target, errorList, 1)
    }


    fun case2(
        target: List<String>,
        errorList: List<String>
    ): Boolean {
        return containCount(target, errorList, 2)
    }


    fun case4(
        target: List<String>,
        errorList: List<String> = listOf<String>("D", "E", "B")
    ): Boolean {
        return containCount(target, errorList, 0)
    }

}