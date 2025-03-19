package flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DeviceConnHelper(
    private val scope: CoroutineScope
) {
    val deviceMutableStateFlow = MutableStateFlow<TutkDevice?>(null)

    private val mDevice = TutkDevice.getInstance("info.uid", "info.deviceAlias", "decodePassword")

    private val listener = TutkDevice.DeviceConnectSuccessCallback { connectState ->
        println("connectState $connectState")
        deviceMutableStateFlow.value = if (connectState) mDevice else null
        if (!connectState) {
            scope.launch {
                delay(3000)
                createConn()
            }

        }
    }

    init {
        scope.launch {
            createConn()
        }
    }

    private suspend fun createConn() {
        mDevice.initConnectSuccessListener(listener)
        mDevice.connectDevice()

    }
}