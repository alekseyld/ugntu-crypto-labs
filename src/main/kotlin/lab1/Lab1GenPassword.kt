package lab1

import kotlin.random.Random

/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

class Lab1GenPassword(
    private val useCapitalEnglishChars: Boolean,
    private val useLowerCaseEnglishChars: Boolean,
    private val useDigits: Boolean
) {

    private val alphabet: List<Char> = buildList {
        if (useCapitalEnglishChars) addAll(capitalEnglishChars)
        if (useLowerCaseEnglishChars) addAll(lowerCaseEnglishChars)
        if (useDigits) addAll(digits)
    }

    val alphabetComplexity: Int
        get() = alphabet.size

    fun generate(length: Int): String {

        val random = Random(System.currentTimeMillis())

        return buildString {
            repeat(length) {
                val index = random.nextInt(0, alphabet.size)
                append(alphabet[index])
            }
        }
    }

    companion object {
        private val capitalEnglishChars = 'A'..'Z'
        private val lowerCaseEnglishChars = 'a'..'z'
        private val digits = '0'..'9'
    }
}

fun main() {
    /** A = 24, L = 7 **/
    val generatorA24 = Lab1GenPassword(
        useLowerCaseEnglishChars = true,
        useCapitalEnglishChars = false,
        useDigits = false
    )
    println("A = ${generatorA24.alphabetComplexity}")
    val password7 = generatorA24.generate(7)
    println("password = $password7")
    println()

    /** A = 52, L = 7 **/
    val generatorA52 = Lab1GenPassword(
        useLowerCaseEnglishChars = true,
        useCapitalEnglishChars = true,
        useDigits = false
    )
    println("A = ${generatorA52.alphabetComplexity}")
    val passwordA52L7 = generatorA52.generate(7)
    println("password = $passwordA52L7")
    println()

    /** A = 36, L = 6 **/
    val generatorA62 = Lab1GenPassword(
        useLowerCaseEnglishChars = true,
        useCapitalEnglishChars = true,
        useDigits = true
    )
    println("A = ${generatorA62.alphabetComplexity}")
    val passwordA62L6 = generatorA62.generate(6)
    println("password = $passwordA62L6")
    println()
}