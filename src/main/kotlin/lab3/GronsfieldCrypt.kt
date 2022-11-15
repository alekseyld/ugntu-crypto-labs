package lab3

/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

class GronsfieldCrypt(
    private val key: List<Int>
) {
    private fun getChar(
        char: Char,
        alpha: Int,
        isCrypt: Boolean
    ) = when {
        isCrypt -> {
            val index = alphabet.indexOf(char) + alpha
            val cryptIndex = if (index >= alphabet.size) alphabet.size - index else index
            alphabet[cryptIndex]
        }

        else -> {
            val index = alphabet.indexOf(char) - alpha
            val decryptIndex = if (index < 0) alphabet.size + index else index
            alphabet[decryptIndex]
        }
    }


    private fun process(
        text: String,
        isCrypt: Boolean = false
    ) = buildString {

        val expandedKey = genExpandedKey(text)

        text.lowercase().forEachIndexed { index, c ->
            val char = getChar(c, expandedKey[index], isCrypt)
            append(char)
        }
    }


    fun crypt(text: String) = process(text, true)

    fun decrypt(text: String) = process(text, false)

    private fun genExpandedKey(text: String): List<Int> {
        return if (key.size < text.length) {
            val expandCount = text.length - key.size

            buildList {
                addAll(key)
                repeat(expandCount) {
                    add(key[it % key.size])
                }
            }
        } else key
    }

    companion object {
        private val alphabet = ('a'..'z').toList()
    }
}

fun main() {

    val text = "Amaryllis"
    val key = listOf(2, 0, 2, 3)

    val processor = GronsfieldCrypt(key)

    val cryptText = processor.crypt(text)
    val decryptText = processor.decrypt(cryptText)

    println("Исходная строка: $text")
    println("Зашифрованная: $cryptText")
    println("Расшифрованная: $decryptText")
}