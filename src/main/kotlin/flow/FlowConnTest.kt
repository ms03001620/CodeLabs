package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class DeviceRepository{
    private val scope = CoroutineScope(Job())

    private val connectToDeviceFlow = callbackFlow {
        val device = TutkDevice.getInstance("info.uid", "info.deviceAlias", "decodePassword")

        if (device.isDeviceConnected()) {
            trySend(device)
            close()
        } else {
            device.initConnectSuccessListener { connectState ->
                if (connectState) {
                    trySend(device)
                    close()
                } else {
                    close()
                }
            }
            println("connectDevice")
            device.connectDevice()
        }

        awaitClose {
            device.removeConnectSuccessListener()
        }
    }.shareIn(scope, SharingStarted.Lazily, replay = 1)


    private fun <T> executeDeviceCommand(
        info: String,
        commandBlock: (TutkDevice, (T?) -> Unit) -> Unit
    ): Flow<DeviceCommandResult<T>> = flow {

        executeDeviceCommandByRetry1111(
            info = info,
            commandBlock = commandBlock,
            retryTimes = 0,
        ).collect {
            if (it != null) {
                emit(DeviceCommandResult.Success(it))
            } else {
                emit(DeviceCommandResult.Failure("请求返回值为null"))
            }
        }
    }


    private fun <T> executeDeviceCommandByRetry1111(
        info: String,
        retryTimes: Int = 2,
        baseDelay: Long = 1000,
        commandBlock: (TutkDevice, (T) -> Unit) -> Unit,
    ): Flow<T?> = flow {
        executeCommandBlockFlow(
            device = connectToDeviceFlow.first(),
            commandBlock = commandBlock
        ).collect{
            emit(it)
        }
    }


    private fun <T> executeCommandBlockFlow(
        device: TutkDevice,
        commandBlock: (TutkDevice, (T?) -> Unit) -> Unit
    ) = callbackFlow {
        commandBlock(device) {
            trySend(it)
            close()
        }
        awaitClose()
    }


    fun getNVRDeviceInfo(info: String): Flow<DeviceCommandResult<String>> =
        executeDeviceCommand(info) { device, callback ->
            GlobalScope.launch {
                delay(1000)
                callback("$info, request, resp is ok")
            }
        }

}

sealed class DeviceCommandResult<out T> {
    data class Failure(val message: String) : DeviceCommandResult<Nothing>() // 连接失败
    data class Success<T>(val data: T) : DeviceCommandResult<T>() // 指令执行成功
}


 fun main() = runBlocking {
   val repo = DeviceRepository()

    launch {
        repo.getNVRDeviceInfo("a").collect {
            println(it)
        }
    }

    launch {
        repo.getNVRDeviceInfo("b").collect {
            println(it)
        }
    }


    delay(2000 *1000)
}


/*fun main() = runBlocking {
    // 模拟 c 的初始化过程
    val cFlow = flow {
        println("Initializing c...")
        delay(1000) // 模拟初始化耗时
        emit("Result from c")
    }.shareIn(this, SharingStarted.Lazily, replay = 1) // 使用 SharedFlow 共享结果

    // a 和 b 的流
    val aFlow = flow {
        println("a is waiting for c...")
        val cResult = cFlow.first() // 等待 c 的结果
        emit("a received: $cResult")
    }

    val bFlow = flow {
        println("b is waiting for c...")
        val cResult = cFlow.first() // 等待 c 的结果
        emit("b received: $cResult")
    }

    // 同时收集 a 和 b
    launch {
        aFlow.collect { println(it) }
    }

    launch {
        bFlow.collect { println(it) }
    }

    delay(10000)
}*/




