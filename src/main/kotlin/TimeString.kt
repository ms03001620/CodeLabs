import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit


class TimeString {


}


fun main() {
    val dateString = "2022-10-13T19:30:48.000Z"

    printlnCal(timeStringToTimestamp(dateString, true))
    printlnCal(timeStringToTimestamp(dateString, false))

    println(format(300*1000+2000))
}

fun format(ms: Long): String {
    return String.format("%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(ms) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
        TimeUnit.MILLISECONDS.toSeconds(ms) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
}

fun timeStringToTimestamp(dateString: String, afterAndroidO: Boolean): Long {
    return if (afterAndroidO) {
        Instant.parse(dateString).toEpochMilli()
    } else {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        sdf.timeZone = TimeZone.getTimeZone("UTC");
        val date = sdf.parse(dateString)
        date.time
    }
}

fun printlnCal(milli: Long) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milli

    val yyyy = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val hh = calendar.get(Calendar.HOUR_OF_DAY)
    val mm = calendar.get(Calendar.MINUTE)
    val ss = calendar.get(Calendar.SECOND)

    val zone = calendar.timeZone.id

    println("$yyyy-$month-$day $hh:$mm:$ss  zone:$zone")
}