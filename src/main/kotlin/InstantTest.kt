import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoField
import java.time.temporal.TemporalField
import java.util.Calendar

object InstantTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val ns = System.currentTimeMillis()
        val now = Instant.ofEpochMilli(ns).atZone(ZoneId.systemDefault())
        println(now)

        println("ms:"+ns)
        println("em:"+now.toInstant().toEpochMilli())
        println("es:"+now.toEpochSecond())

        println(LocalDate.now().atStartOfDay(ZoneId.systemDefault()))

    }
}