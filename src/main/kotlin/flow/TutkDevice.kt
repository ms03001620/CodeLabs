package flow

import kotlin.random.Random


class TutkDevice(uid: String, name: String, password: String) {
    var connect = false

    fun isDeviceConnected() = connect

    fun interface DeviceConnectSuccessCallback {
        fun onDeviceConnect(connectState: Boolean)
    }

    private var connectSuccessCallback: DeviceConnectSuccessCallback? = null

    fun initConnectSuccessListener(callback: DeviceConnectSuccessCallback) {
        connectSuccessCallback = callback

    }

    fun connectDevice() {
        Thread{
            Thread.sleep(1000L /**Random.nextInt(5)*/)
            connect = true
            connectSuccessCallback?.onDeviceConnect(true)
        }.start()


        Thread{
            Thread.sleep(3*1000)
            connect = false
            connectSuccessCallback?.onDeviceConnect(false)
        }.start()

/*        Thread{
            Thread.sleep(10*1000)
            connect = true
            connectSuccessCallback?.onDeviceConnect(true)
        }.start()*/
    }

    fun removeConnectSuccessListener() {
        connectSuccessCallback = null
    }


    companion object {
        private var instance: TutkDevice? = null

        fun getInstance(uid: String, name: String, password: String): TutkDevice {
            if (instance == null) {
                instance = TutkDevice(uid, name, password)
            }

            return instance!!
        }

        fun getInstance(): TutkDevice {
            return instance!!
        }
    }
}