package collections

import ClientPerson
import DefaultPerson
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * https://blog.csdn.net/qq_46515305/article/details/124320873
 * https://juejin.cn/post/6844904203010179085
 */
class JsonGsonTest {
    @Test
    fun makeObjByGsonAndWithNullVal() {
        val jsonString = """{"id":100, "name":null}"""

        val clientObj = Gson().fromJson(jsonString, ClientPerson::class.java)

        println(clientObj)

        assertEquals(100, clientObj.id)
        assertNull(clientObj.name)
    }

    @Test
    fun makeObjHappyPath() {
        val jsonString = """{"id":100, "name":"abc"}"""

        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ClientPerson> = moshi.adapter(ClientPerson::class.java)

        val obj = jsonAdapter.fromJson(jsonString)
        assertEquals(100, obj!!.id)
        assertEquals("abc", obj.name)
    }

    @Test(expected= JsonDataException::class)
    fun makeObjWithException() {
        val jsonString = """{"id":100}"""

        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ClientPerson> = moshi.adapter(ClientPerson::class.java)

        println(jsonAdapter.fromJson(jsonString))
    }

    @Test
    fun makeObjByDefaultValue() {
        val jsonString = """{"id":100}"""

        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<DefaultPerson> = moshi.adapter(DefaultPerson::class.java)

        val obj = jsonAdapter.fromJson(jsonString)
        assertEquals("", obj!!.name)
        assertEquals(100, obj.id)
    }

}