package lab3

/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

class CezarDecrypt(
    private val alpha: Int
) {

    private fun getDecryptChar(char: Char): Char {
        if (char.isWhitespace()) return char
        if (char.isDigit()) return char

        val index = alphabet.indexOf(char) - alpha

        val decryptIndex = if (index < 0) alphabet.size + index else index

        return alphabet[decryptIndex]
    }

    fun decrypt(text: String) = buildString {
        text.forEach {
            this.append(getDecryptChar(it))
        }
    }

    companion object {
        private val alphabet = ('a'..'z').toList()
    }
}

fun main() {

    val text = "lqaocqam qa i bzqks ivl asqtt"
    val alpha = 8

    val result = CezarDecrypt(alpha).decrypt(text)

    println("Исходная строка: $text")
    println("Сдвиг: $alpha")
    println("Результат: $result")
}