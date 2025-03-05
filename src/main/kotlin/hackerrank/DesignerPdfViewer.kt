package hackerrank

class DesignerPdfViewer {
}

fun main() {

    println(
        8 == designerPdfViewer(
            arrayOf(1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 1, 1, 5, 5, 1, 5, 2, 5, 5, 5, 5, 5, 5), "torn"
        )
    )

    println(
        9 == designerPdfViewer(
            arrayOf(1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5), "abc"
        )
    )

    println(
        28 == designerPdfViewer(
            arrayOf(1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7), "zaba"
        )
    )

}


fun designerPdfViewer(h: Array<Int>, word: String): Int {
    val ascA = 97
    val targetHeight = word.map {
        h[it.code - ascA]
    }
    return targetHeight.max() * targetHeight.size
}