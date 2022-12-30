package tasks

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

data class Report<T>(val totalMs: Long, val depends: ArrayList<T>)

class DelayDTaskManager<T> {

    private val list = ArrayList<RunTask<T>>()

    fun add(task: DependsTask<T>) {
        list.add(task)
    }

    fun run(coroutineScope: CoroutineScope, callback: (Report<T>) -> Unit) {
        coroutineScope.launch {
            val jobsList = ArrayList<Deferred<T>>()
            val depends = ArrayList<T>()

            val pass = measureTimeMillis {
                list.forEach { task ->
                    if (task.type() == RunType.ASYNC) {
                        val p = async {
                            task.execute(depends)
                        }
                        jobsList.add(p)
                    } else {
                        depends.addAll(runAsyncTasks(jobsList))
                        depends.add(task.execute(depends))
                    }
                }
                if (jobsList.isNotEmpty()) {
                    depends.addAll(runAsyncTasks(jobsList))
                }
            }
            assert(jobsList.isEmpty())
            val report = Report<T>(pass, depends)
            callback.invoke(report)
        }
    }

    suspend fun run(coroutineScope: CoroutineScope) = suspendCoroutine<Report<T>> { continuation ->
        run(coroutineScope) {
            continuation.resume(it)
        }
    }

    suspend fun runAsyncTasks(async: ArrayList<Deferred<T>>): ArrayList<T> {
        val depends = ArrayList<T>()
        async.forEach { asyncJob ->
            depends.add(asyncJob.await())
        }
        async.clear()//异步任务执行完毕后已经变成了depends
        return depends
    }

}