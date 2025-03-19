package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking



fun main() = runBlocking {
    val repo = DeviceRepository(this)

    launch {
        println("request a")
        repo.getNVRDeviceInfo("a").collect {
            println(it)
        }
    }

    launch {
        println("request b")
        repo.getNVRDeviceInfo("b").collect {
            println(it)
        }
    }

    launch {
        delay(3000)
        println("request c")
        repo.getNVRDeviceInfo("c").collect {
            println(it)
        }
    }


    delay(10* 1000)

}

