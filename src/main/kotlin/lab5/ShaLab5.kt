package lab5

import kotlin.math.pow


/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

fun Int.pow(n: Int): Double {
    return this.toDouble().pow(n)
}

fun Double.mod(other: Int): Int {
    return this.mod(other.toDouble()).toInt()
}

class ShaCoder(
    val p: Int,
    val q: Int
) {

    val n = p * q

    // функция Эйлера
    val fi = (p - 1) * (q - 1)

    val e = 5
    val d = findD()

    private fun calculateD(k: Int) = (k * fi + 1) / e.toDouble()

    private fun findD(): Int {
        var k = 1
        while (true) {
            val d = calculateD(k)
            if (d.mod(e) == 1) {
                return d.toInt()
            }
            k++
        }
    }

    private fun encodeSha(i: Int) = i.pow(e).mod(n)
    private fun decodeSha(i: Int) = i.pow(d).mod(n)

    fun encode(text: String): List<Int> {
        return text
            .map { alphabet.indexOf(it) + 1 }
            .map { encodeSha(it) }
    }

    companion object {
        val alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
    }
}

fun main() {

    print("Введите p=")
    val p = readln().toInt()

    print("Введите q=")
    val q = readln().toInt()

    print("Введите текст для шифрования =")
    val text = readln()

    val coder = ShaCoder(p, q)
    val coded = coder.encode(text)

    println("d=${coder.d}")
    println("Результат: $coded")
}

