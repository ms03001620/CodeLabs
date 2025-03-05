package hackerrank

import java.lang.IllegalStateException
import java.util.LinkedList

class DrawingBook {
}

fun main() {

/*    createPage(1, 1).let {
        println(it.countValidPage()==1)
        println(it.isContainPageNumber(1))
    }

    createPage(2, 2).let {
        println(it.countValidPage()==1)
        println(it.isContainPageNumber(2))
    }

    createPage(2, 3).let {
        println(it.countValidPage()==2)
        println(it.isContainPageNumber(2))
        println(it.isContainPageNumber(3))
    }

    makeList(1).let {
        println(it.size==1)
        println(it.first().countValidPage()==1)
    }

    makeList(2).let {
        println(it.size == 2)
        println(it.first().countValidPage() == 1)
        println(it.last().countValidPage() == 1)
    }

    makeList(3).let {
        println(it.size == 2)
        println(it.first().countValidPage() == 1)
        println(it.last().countValidPage() == 2)
    }

    makeList(4).let {
        println(it.size == 3)
        println(it[0].countValidPage() == 1)
        println(it[1].countValidPage() == 2)
        println(it[2].countValidPage() == 1)
    }*/

    println(pageCount(5, 3) == 1)

    println(pageCount(6, 2) == 1)

    println(pageCount(5, 4) == 0)
}

fun pageCount(n: Int, p: Int): Int {
    val list = makeList(n)
    var start = 0
    var end = 0

    list.forEachIndexed { index, page ->
        if (page.isContainPageNumber(p)) {
            start = index
        }
    }

    list.reversed().forEachIndexed { index, page ->
        if (page.isContainPageNumber(p)) {
            end = index
        }
    }

    return Math.min(start, end)
}

fun makeList(page: Int): List<Page> {
    val list = LinkedList<Page>()
    var currentPage = 1

    while (currentPage <= page) {
        val newPage = createPage(currentPage, page)

        currentPage += newPage.countValidPage()
        list.add(newPage)
    }
    return list
}


fun createPage(number: Int, maxPage: Int): Page {
    return if (number % 2 == 0) {
        if (number < maxPage) {
            Page(number, number + 1)
        } else {
            Page(number, null)
        }
    } else {
        if (number == 1) {
            Page(null, 1)
        } else {
            Page(number - 1, number)
        }
    }
}

class Page(
    private val left: Int?,
    private val right: Int?
) {
    fun isContainPageNumber(number: Int): Boolean {
        return left == number || right == number
    }

    fun countValidPage(): Int {
        if (left == null && right == null) {
            throw IllegalStateException("invalid page")
        }
        return if (left == null || right == null) 1 else 2
    }
}