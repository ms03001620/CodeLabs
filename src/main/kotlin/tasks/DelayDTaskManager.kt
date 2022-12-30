package tasks

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class DelayDTaskManager<T> {

    private val list = ArrayList<RunTask<T>>()

    fun add(task: DependsTask<T>) {
        list.add(task)
    }

    suspend fun run(coroutineScope: CoroutineScope, callback: (ArrayList<T>) -> Unit) {
        coroutineScope.launch {
            val jobsList = ArrayList<Deferred<T>>()
            val depends = ArrayList<T>()

            val pass = measureTimeMillis {
                list.forEach { task ->
                    if (task.type() == RunType.ASYNC) {
                        val p = async {
                            task.execute(null)
                        }
                        jobsList.add(p)
                    } else {
                        depends.addAll(runAsyncTasks(jobsList))
                        depends.add(task.execute(depends))
                    }
                }
            }
            println(pass)
            callback.invoke(depends)
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