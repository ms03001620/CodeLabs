package tasks

import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class DelayDTaskManagerTest {
    private val testDispatcher = UnconfinedTestDispatcher()


    @Test
    fun test2Async1Sync() = runTest(testDispatcher) {
        val m = DelayDTaskManager<String>()
        m.add(DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                return MainTest.asyncTask1()
            }
        }))
        m.add(DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                return MainTest.asyncTask2()
            }
        }))
        m.add(DependsTask(RunType.SYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                return MainTest.asyncTask3(depends).toString()
            }
        }))
        val report = m.run(this)
        println(report)
        assertTrue(report.depends[0].length == 10)
        assertTrue(report.depends[1].length == 10)
        assertEquals("20", report.depends[2])
    }
}


object MainTest {

    suspend fun asyncTask1(): String {
        delay(1000)
        return "asyncTask1"
    }

    suspend fun asyncTask2(): String {
        delay(1000)
        return "asyncTask2"
    }

    suspend fun asyncTask3(depends: ArrayList<String>?): Int {
        delay(1000)

        var count = 0
        depends?.forEach {
            count += it.length
        }

        return count
    }
}
