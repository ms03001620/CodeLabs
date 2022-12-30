package tasks


interface OnDependsCall<T> {
    suspend fun onCall(depends: ArrayList<T>): T
}

enum class RunType(s: String) {
    SYNC("sync"), ASYNC("async")
}

interface RunTask<T> {
    fun type(): RunType
    //执行任务，可能会附加依赖结果
    suspend fun execute(depends: ArrayList<T>): T
}

class DependsTask<T>(private val type: RunType, private val onCall: OnDependsCall<T>) : RunTask<T> {
    override fun type() = type
    override suspend fun execute(depends: ArrayList<T>): T {
        return onCall.onCall(depends)
    }
}
