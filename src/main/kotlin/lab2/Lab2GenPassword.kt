package lab2

import kotlin.math.pow
import kotlin.random.Random

/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

class Lab2GenPassword(
    private val identifier: String
) {

    private val random = Random(System.currentTimeMillis())

    private fun generateB8() : Char {
        val n = identifier.length.toDouble()
        val p = n.pow(2.0).mod(10.0) + n.pow(3.0).mod(10.0) + 1

        return lowerCaseCyrillicChars[p.toInt()]
    }

    fun generate(): String {
        return buildString {

            digits.random(random).also { b1 -> append(b1) }
            digits.random(random).also { b2 -> append(b2) }
            digits.random(random).also { b3 -> append(b3) }

            specialChars.random(random).also { b4 -> append(b4) }
            specialChars.random(random).also { b5 -> append(b5) }
            specialChars.random(random).also { b6 -> append(b6) }

            capitalEnglishChars.random(random).also { b7 -> append(b7)  }

            generateB8().also { b8 -> append(b8) }
        }
    }

    companion object {
        private val capitalEnglishChars = 'A'..'Z'
        private val lowerCaseCyrillicChars = ('а'..'я').toList()
        private val digits = '0'..'9'
        private val specialChars = listOf('!', '”', '#','$','%','&','’','(',')','*')
    }
}

fun main() {

    print("Enter identifier for generator: ")
    val identifier = readln()
    println()

    val generator = Lab2GenPassword(identifier)

    repeat(10) {

        println("Password generation $it: ${generator.generate()}")
    }
}