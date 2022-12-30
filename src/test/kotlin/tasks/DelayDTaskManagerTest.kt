package tasks

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DelayDTaskManagerTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    @Test
    fun run() = runTest(testDispatcher) {
        val t1 = DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                return MainTest.asyncTask1()
            }
        })

        val t2 = DependsTask(RunType.ASYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                return MainTest.asyncTask2()
            }
        })

        val t3 = DependsTask(RunType.SYNC, object : OnDependsCall<String> {
            override suspend fun onCall(depends: ArrayList<String>?): String {
                return MainTest.asyncTask3(depends).toString()
            }
        })

        val m = DelayDTaskManager<String>()
        m.add(t1)
        m.add(t2)
        m.add(t3)
        m.run(this) {
            println(it)
        }
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
