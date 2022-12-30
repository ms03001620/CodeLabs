package tasks

import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

//https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/
@OptIn(ExperimentalCoroutinesApi::class)
class DelayDTaskManagerTest {
    private val testDispatcher = UnconfinedTestDispatcher()


    @Test
    fun test2Async1Sync() = runTest(testDispatcher) {
        val m = DelayDTaskManager<String>()
        m.add(DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                delay(1000)
                return "asyncTask1"
            }
        }))
        m.add(DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                delay(1000)
                return "asyncTask2"
            }
        }))
        m.add(DependsTask(RunType.SYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                delay(1000)
                var count = 0
                depends?.forEach {
                    count += it.length
                }
                return count.toString()
            }
        }))
        val report = m.run(this)
        println(report)
        assertTrue(report.depends[0].length == 10)
        assertTrue(report.depends[1].length == 10)
        assertEquals("20", report.depends[2])
        assertEquals(2000, currentTime)// 2(async) = 1000, 1(sync) = 1000
    }

    @Test
    fun test2Async() = runTest(testDispatcher) {
        val m = DelayDTaskManager<String>()
        m.add(DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                delay(1000)
                return "asyncTask1"
            }
        }))
        m.add(DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                delay(1000)
                return "asyncTask2"
            }
        }))
        val report = m.run(this)
        println(report)
        assertTrue(report.depends[0].length == 10)
        assertTrue(report.depends[1].length == 10)
        assertEquals(1000, currentTime)// 2(async) = 1000
    }

    @Test
    fun test2sync() = runTest(testDispatcher) {
        val m = DelayDTaskManager<String>()
        m.add(DependsTask(RunType.SYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                delay(1000)
                return "asyncTask1"
            }
        }))
        m.add(DependsTask(RunType.SYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                delay(1000)
                return "asyncTask2"
            }
        }))
        val report = m.run(this)
        println(report)
        assertTrue(report.depends[0].length == 10)
        assertTrue(report.depends[1].length == 10)
        assertEquals(2000, currentTime)// 2(sync) = 2000
    }

}

