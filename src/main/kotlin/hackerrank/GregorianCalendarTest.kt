package hackerrank

import java.time.Instant
import java.util.*

class GregorianCalendarTest {
}

fun main() {

    println(31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 13)
    println(31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 12)

    val year = 2016
    val yearChange = 1919

    val d: GregorianCalendar = GregorianCalendar(year, 1, 1)
    d.set(GregorianCalendar.DAY_OF_YEAR, 256)

    if (isLeap(year, yearChange)) {
        d.add(GregorianCalendar.DAY_OF_YEAR, -1)
    }

    println(formatCal(d))

}

fun formatCal(cal: Calendar): String {
    return "${cal.get(Calendar.DAY_OF_MONTH)}.${cal.get(Calendar.MONTH)+1}.${cal.get(Calendar.YEAR)}"
}




//  println("1800 isLeap:${d.isLeapYear(1800)}")
//   println("gregorianChange @ ${d.gregorianChange}")
