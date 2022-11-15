package lab3

/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

class SubDecrypt {

    fun decrypt(text: String) = buildString {
        text.forEach { append(table[it]) }
    }

    companion object {

        private val alphabet = buildList {
            add(' ')
            addAll('a'..'z')
        }

        private val cryptSymbols = setOf(
            '!', '@', '#', '$', '%', '^', '&', '*', '(',
            ')', '-', '=', '+', '?', ':', ';', '<', '>',
            '/', '[', ']', '{', '}', '|', '.', ','
        )

        private val table = cryptSymbols.zip(alphabet).toMap()
    }
}

fun main() {

    val text = "!+)=^!<+@,):*!};++^,#@++!@:%!<):*!<;:*"

    val result = SubDecrypt().decrypt(text)

    println("Исходная строка: $text")
    println("Результат: $result")
}