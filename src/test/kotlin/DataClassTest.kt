package collections

import org.junit.Assert.assertTrue
import org.junit.Test

class DataClassTest {
    data class Address(val address: String)
    data class User(val name: String, val age: Int, val heightCM: Float, val address: Address)

    @Test
    fun componentNTest() {
        val address = Address("dalian")
        val u = User("mark", 37, 174.5f, address)
        println(u)

        println(u.component1())
        println(u.component2())
        println(u.component3())
        println(u.component4())
    }

    @Test
    fun copyTest() {
        val address = Address("dalian")
        val u = User("mark", 37, 174.5f, address)
        val y = u.copy(name = "ma")

        assertTrue(u.address ===y.address)
    }
}