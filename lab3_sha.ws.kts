import kotlin.math.pow

fun Int.pow(n: Int) : Double {
    return this.toDouble().pow(n)
}

fun Double.mod(other: Int) : Int {
    return this.mod(other.toDouble()).toInt()
}

val text = "переучеба"
val p = 17
val q = 37

val n = p * q
n

// функция Эйлера
val fi = (p - 1) * (q - 1)
fi

val e = 5
e

// вычисление закрытого ключа
fun fD(k: Int) = (k * fi + 1) / e.toDouble()

fD(1)
fD(2)
fD(3)
val d = fD(4).toInt()
d

val alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"

// коды символов
val input = text.map { alphabet.indexOf(it) + 1 }
input

fun encodeSha(i: Int) = i.pow(e).mod(n)

val encodedData = input.map { encodeSha(it) }
encodedData // зашифрованные данные

fun decodeSha(i: Int) = i.pow(d).mod(n)

val decodedData = encodedData.map { decodeSha(it) }
encodedData // декодированные данные

/** Хэширование и проверка */

val hash = mutableListOf<Int>(0)
var h = 0

for ((i, v) in input.withIndex()) {
    (hash[i] + v)
        .toDouble()
        .pow(2.0)
        .mod(n.toDouble()).also {
            it.toInt().apply {
                hash.add(this)
                if (i == input.lastIndex) {
                    h = this
                }
            }
        }
}

hash
h

// S
val s = 596.toBigDecimal()
    .pow(d).toBigInteger()
    .mod(n.toBigInteger())
    .toInt()
s

val hd = s.pow(e).mod(n)
hd

h == hd