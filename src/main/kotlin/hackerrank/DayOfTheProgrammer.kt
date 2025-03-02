package hackerrank

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DayOfTheProgrammer {
}

fun main() {
    println(dayOfProgrammer(1984) == "12.09.1984")
    println(dayOfProgrammer(2017) == "13.09.2017")
    println(dayOfProgrammer(2016) == "12.09.2016")
    println(dayOfProgrammer(1800) == "12.09.1800")
}

fun dayOfProgrammer(year: Int): String {
    val cal = GregorianCalendar()
    cal.set(Calendar.YEAR, year)
    cal.set(Calendar.DAY_OF_YEAR, 256)

    if(year == 1700 || year == 1800 || year == 1900){
        cal.add(Calendar.DAY_OF_MONTH, -1)
    }else if(year == 1918){
        cal.add(Calendar.DAY_OF_MONTH, 13)
    }

    var format1 = SimpleDateFormat("dd.MM.yyyy")
    var formatted = format1.format(cal.getTime())

    return formatted
}

fun getCalFormatString(calendar: Calendar) =
    SimpleDateFormat("dd.MM.yyyy").format(calendar.time)


fun isLeap(year: Int, changeYear: Int): Boolean {
    if (year >= changeYear) {
        return false
    }

    val d: GregorianCalendar = GregorianCalendar()
    d.gregorianChange = Date.from(Instant.parse("${changeYear}-01-01T00:00:00.00Z"))
    return d.isLeapYear(year)
}




fun dayOfProgrammerBak(year: Int, programmerDay: Long = 256): String {
    val isLeapOfJulian = year <= 1800 && year % 4 == 0

    val day = if (!isLeapOfJulian) programmerDay - 1 else programmerDay - 2
    val da = LocalDate.of(year, 1, 1)

    return DateTimeFormatter
        .ofPattern("dd.MM.yyyy")
        .format(da.plusDays(day))
}

