package collections

import org.junit.Test
import java.lang.StringBuilder
import java.lang.UnsupportedOperationException
import kotlin.test.assertEquals

class HR_camel_case {

    class CamelClass(private val fullCommandString: String) {
        enum class Command {
            S, C
        }

        enum class NameType {
            M, C, V
        }

        class FullCommand(val command: Command, val type: NameType, val source: String)

        private val fullCommand: FullCommand

        init {
            val fullCommandArray = fullCommandString.split(";")
            if (fullCommandArray.size != 3) {
                throw IllegalArgumentException("error constructor command:$fullCommandString")
            }

            fullCommand = FullCommand(
                command = decodeCommand(fullCommandArray[0]),
                type = decodeType(fullCommandArray[1]),
                source = fullCommandArray[2]
            )
        }

        fun decodeType(typeChar: String): NameType {
            if (typeChar.equals("m", ignoreCase = true)) {
                return NameType.M
            }
            if (typeChar.equals("c", ignoreCase = true)) {
                return NameType.C
            }
            if (typeChar.equals("v", ignoreCase = true)) {
                return NameType.V
            }
            throw UnsupportedOperationException("error typeChar :$typeChar")
        }

        fun decodeCommand(commandChar: String): Command {
            if (commandChar.equals("s", ignoreCase = true)) {
                return Command.S
            }
            if (commandChar.equals("c", ignoreCase = true)) {
                return Command.C
            }
            throw UnsupportedOperationException("error commandChar :$commandChar")
        }


        fun convert(): String {
            when (fullCommand.command) {
                Command.S -> {
                    //deleteParentheses
                    return deleteParentheses(fullCommand.source).let {
                        addSpace(it)
                    }.let {
                        lowcase(it)
                    }
                }

                Command.C -> {
                    val words = fullCommand.source.split(" ")
                    return cWord(fullCommand.type, words)
                }

                else -> {
                    throw UnsupportedOperationException("invalid command:${fullCommand.command}")
                }
            }
        }

        fun cWord(nameType: NameType, words: List<String>): String {
            val result = StringBuilder()
            for (index in words.indices) {
                val word = words[index]
                val isFirst = index == 0
                val isLast = index == (words.size - 1)
                if (isFirst) {
                    result.append(if (nameType == NameType.C) word.capitalize() else word)
                } else {
                    result.append(word.capitalize())
                }
                if (nameType == NameType.M) {
                    if (isLast) {
                        result.append("()")
                    }
                }
            }

            return result.toString()
        }

        fun deleteParentheses(source: String) = source.replace("()", "")

        fun addSpace(source: String): String {
            val result = StringBuilder()
            source.forEach {
                if (it.isUpperCase()) {
                    result.append(" ")
                    result.append(it)
                } else {
                    result.append(it)
                }
            }

            if (result.startsWith(" ")) {
                result.delete(0, 1)
            }
            return result.toString()
        }

        fun lowcase(source: String) = source.toLowerCase()

    }


    @Test
    fun componentNTest() {
        /* S;M;plasticCup()
           S;V;pictureFrame
           S;C;LargeSoftwareBook

           C;V;mobile phone
           C;C;coffee machine
           C;M;white sheet of paper
          */

        assertEquals("plastic cup", CamelClass("S;M;plasticCup()").convert())
        assertEquals("large software book", CamelClass("S;C;LargeSoftwareBook").convert())
        assertEquals("picture frame", CamelClass("S;V;pictureFrame").convert())

        assertEquals("mobilePhone", CamelClass("C;V;mobile phone").convert())
        assertEquals("mobilePhone()", CamelClass("C;M;mobile phone").convert())
        assertEquals("MobilePhone", CamelClass("C;C;mobile phone").convert())

        assertEquals("CoffeeMachine", CamelClass("C;C;coffee machine").convert())
        assertEquals("whiteSheetOfPaper()", CamelClass("C;M;white sheet of paper").convert())


        assertEquals("frame", CamelClass("S;V;frame").convert())

        assertEquals("frame", CamelClass("C;V;frame").convert())
        assertEquals("Frame", CamelClass("C;C;frame").convert())
        assertEquals("frame()", CamelClass("C;M;frame").convert())

/*        val sc  = Scanner(System.`in`)

        while(sc.hasNext()) {
            println(CamelClass(sc.nextLine()).convert())
        }*/


    }
}