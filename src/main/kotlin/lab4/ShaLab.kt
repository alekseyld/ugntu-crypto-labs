package lab4

import java.io.ByteArrayOutputStream
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.util.Base64
import javax.crypto.Cipher

/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

object ShaUtils {

    const val ALGORITHM = "RSA"
    private const val DECRYPT_MAX_SIZE = 256

    fun encrypt(text: String, publicKey: PublicKey): String {

        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val byteArray = cipher.doFinal(text.toByteArray(Charsets.UTF_8))

        return String(Base64.getEncoder().encode(byteArray))
    }

    fun decrypt(text: String, privateKey: PrivateKey): Any {
        val input = Base64.getDecoder().decode(text)

        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val byteArray = cipher.doFinalWithBuffer(
            byteArray = input,
            bufferMaxSize = DECRYPT_MAX_SIZE
        )

        return String(byteArray)
    }

    private fun Cipher.doFinalWithBuffer(
        byteArray: ByteArray,
        bufferMaxSize: Int
    ): ByteArray {
        var temp: ByteArray
        var offset = 0

        val outputStream = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) {
            if (byteArray.size - offset >= bufferMaxSize) {
                temp = this.doFinal(byteArray, offset, bufferMaxSize)
                offset += bufferMaxSize
            } else {
                temp = this.doFinal(byteArray, offset, (byteArray.size - offset))
                offset = byteArray.size
            }
            outputStream.write(temp)
        }
        outputStream.close()

        return outputStream.toByteArray()
    }

}

fun main() {

    val text = "Лысов"

    println("input = $text")
    println("\n")

    val generator = KeyPairGenerator.getInstance(ShaUtils.ALGORITHM)

    val genKeyPair = generator.genKeyPair()
    val privateKey = genKeyPair.private
    val publicKey = genKeyPair.public

    println(privateKey)
    println(publicKey)
    println("\n")

    val encrypted = ShaUtils.encrypt(text, publicKey)
    val decrypted = ShaUtils.decrypt(encrypted, privateKey)

    println("encrypted = $encrypted")
    println("decrypted = $decrypted")
}