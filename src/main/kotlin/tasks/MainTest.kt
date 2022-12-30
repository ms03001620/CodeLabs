package tasks

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

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

fun main(args: Array<String>) = runBlocking {
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
    m.run(this) {
        println(it)
    }
}

fun main1(args: Array<String>) = runBlocking {

    val job1 = async {
        MainTest.asyncTask1()
    }

    val job2 = async {
        MainTest.asyncTask2()
    }


    val pass = measureTimeMillis {
        println(job1.await() + job2.await())
    }
    println(", pass:$pass")
}