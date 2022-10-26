import java.text.SimpleDateFormat
import java.util.*

class HackCoode {

}


fun main() {
   //miniMaxSum(arrayOf(1, 2, 3))

    val ss = "12:01:00AM"
   //val ss = "12:01:00PM"
   // val ss = "07:05:45PM"


    println(timeConvert(ss))
}

fun timeConvert(twelveHoursTime: String) =
    SimpleDateFormat("HH:mm:ss").format(SimpleDateFormat("hh:mm:ssa", Locale.US).parse(twelveHoursTime))

fun timeConversionV1(s: String): String {
    val date = SimpleDateFormat("h:mm:ssa", Locale.US).parse(s)

    val format = SimpleDateFormat("HH:mm:ss", Locale.US)

    return format.format(date).toString()
}

fun timeConversion(s: String): String {
    // Write your code here
    //convert input string 12 format to Dateformatter
    val time12Formatter = SimpleDateFormat("hh:mm:ssaa")
    val time12InputFormat = time12Formatter.parse(s)

    //set Date 12 to 24 format
    val time24Formatter = SimpleDateFormat("HH:mm:ss")
    val time24OutputFormat = time24Formatter.format(time12InputFormat)

    return time24OutputFormat
}

fun miniMaxSum(arr: Array<Int>): Unit {
    // Write your code here

    val size = arr.size
    val listMax = size -1

    val minList = mutableListOf<Long>()
    val maxList = mutableListOf<Long>()

    for(data in arr){
        updateList(minList, data.toLong(), listMax.toLong())
        updateListMax(maxList,data.toLong(), listMax.toLong())
    }

    println("${minList.sum()} ${maxList.sum()}")
}

fun updateList(list: MutableList<Long>, current: Long, maxList: Long) {
    val min = if (list.isEmpty()) {
        null
    } else {
        list.minBy { it }
    }

    min?.let {
        if (list.size < maxList) {
            list.add(current)
        } else {
            val max = list.maxBy { it }
            max?.let {
                if (current < max) {
                    list.remove(max)
                    list.add(current)
                }
            }
        }
    } ?: run {
        list.add(current)
    }
}

fun updateListMax(list: MutableList<Long>, current: Long, maxList: Long) {
    val min = if (list.isEmpty()) {
        null
    } else {
        list.maxBy { it }
    }

    min?.let {
        if (list.size < maxList) {
            list.add(current)
        } else {
            val max = list.minBy { it }
            max?.let {
                if (current > max) {
                    list.remove(max)
                    list.add(current)
                }
            }
        }
    } ?: run {
        list.add(current)
    }
}