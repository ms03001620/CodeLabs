package hackerrank

class AppendAndDelete {
}

fun main() {
    println("YES" == appendAndDelete("abc", "def", 6))



}

fun appendAndDelete(s: String, t: String, k: Int): String {
    val h = k / 2
    if(s.length<=h){
        return "YES"
    }



    return "NO"
}