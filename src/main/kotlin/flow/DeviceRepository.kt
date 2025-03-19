package flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class DeviceCommandResult<out T> {
    data class Failure(val message: String) : DeviceCommandResult<Nothing>() // 连接失败
    data class Success<T>(val data: T) : DeviceCommandResult<T>() // 指令执行成功
}


class DeviceRepository(
    private val scope: CoroutineScope
) {
    private val connHelper = DeviceConnHelper(scope)

    private fun <T> executeDeviceCommand(
        commandBlock: (TutkDevice, (T?) -> Unit) -> Unit
    ): Flow<DeviceCommandResult<T>> = flow {

        executeWithDevice(
            commandBlock = commandBlock,
        ).collect {
            if (it != null) {
                emit(DeviceCommandResult.Success(it))
            } else {
                emit(DeviceCommandResult.Failure("请求返回值为null"))
            }
        }
    }


    private fun <T> executeWithDevice(
        commandBlock: (TutkDevice, (T) -> Unit) -> Unit,
    ): Flow<T?> = flow {
        executeCommandBlockFlow(
            device = connHelper.deviceMutableStateFlow.filterNotNull().first(),
            commandBlock = commandBlock
        ).collect {
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
        executeDeviceCommand { device, callback ->
            scope.launch {
                delay(1000)
                callback("$info, request, resp is ok")
            }
        }

}

