import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Unconfined

class CorContext {
}

/*
fun main() {
    val parentJob = GlobalScope.launch {
        //childJob是一个SupervisorJob
        val childJob = launch(SupervisorJob()) {
            throw NullPointerException()
        }
        childJob.join()
        println("parent complete")
    }
    Thread.sleep(1000)
}
*/



/*
fun main() {
    val scope = CoroutineScope(CoroutineName("Coroutine-Name") + Dispatchers.IO)

    val job = scope.launch(start = CoroutineStart.DEFAULT) {
        println("hello world:${Thread.currentThread().name}")
    }

    //进程保活1s，只有进程存活的前提下，协程才能会启动和执行
    Thread.sleep(1000)

    println("isActive:${job.isActive}")
    println("isCompleted:${job.isCompleted}")
    println("isCancelled:${job.isCancelled}")
}

*/

/*fun main(){
    GlobalScope.launch(Dispatchers.Unconfined){
        println(Thread.currentThread().name)

        //挂起
        withContext(Dispatchers.IO){
            println(Thread.currentThread().name)
        }
        //恢复
        println(Thread.currentThread().name)

        //挂起
        withContext(Dispatchers.Default){
            println(Thread.currentThread().name)
        }
        //恢复
        println(Thread.currentThread().name)
    }

    //进程保活
    Thread.sleep(1000)
}*/

/*
fun main(args: Array<String>) = runBlocking{
    // 1. 程序开始
    println("${Thread.currentThread().name}: 1");

    // 2. 启动一个协程, 并立即启动
    launch(Unconfined) { // Unconfined意思是在当前线程(主线程)运行协程
        // 3. 本协程在主线程上直接开始执行了第一步
        println("${Thread.currentThread().name}: 2");

        */
/* 4. 本协程的第二步调用了一个suspend方法, 调用之后,
         * 本协程就放弃执行权, 遣散运行我的线程(主线程)请干别的去.
         *
         * delay被调用的时候, 在内部创建了一个计时器, 并设了个callback.
         * 1秒后计时器到期, 就会调用刚设置的callback.
         * 在callback里面, 会调用系统的接口来恢复协程.
         * 协程在计时器线程上恢复执行了. (不是主线程, 跟Unconfined有关)
         *//*

        delay(1000L)  // 过1秒后, 计时器线程会resume协程

        // 7. 计时器线程恢复了协程,
        println("${Thread.currentThread().name}: 4")
    }

    // 5. 刚那个的协程不要我(主线程)干活了, 所以我继续之前的执行
    println("${Thread.currentThread().name}: 3");

    // 6. 我(主线程)睡2秒钟
    Thread.sleep(2000L)

    // 8. 我(主线程)睡完后继续执行
    println("${Thread.currentThread().name}: 5");
}*/

