import java.io.InputStream


class ReadCvs {
    fun readCsv(inputStream: InputStream) {
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()
        val c = reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (mID, sId, start, end) = it.split(',', ignoreCase = false, limit = 4)
                sId
            }.toHashSet().count()
        println("Count:$c")
    }

    fun aaa() {
        val input = this.javaClass.getResourceAsStream("v1.csv")
        //val input = this.javaClass.getResourceAsStream("v2.xlsx")
        assert(input!=null)
        readCsv(input)
    }

}


fun main() {
    ReadCvs().aaa()
}
