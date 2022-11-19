package lab5

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.Signature


/**
 *  Author: Лысов А.Д.
 *  Group: БПИз-18-01
 */

fun main() {

    print("Введите текст = ")
    val text = readln()

    print("Проверочный текст = ")
    val checkMessage = readln()

    val generator = KeyPairGenerator.getInstance("RSA")
    generator.initialize(512)
    val pair: KeyPair = generator.generateKeyPair()

    val privateSignature: Signature = Signature.getInstance("SHA256withRSA")
    privateSignature.initSign(pair.private)
    privateSignature.update(text.toByteArray(Charsets.UTF_8))

    val signature: ByteArray = privateSignature.sign()

    val publicSignature: Signature = Signature.getInstance("SHA256withRSA")
    publicSignature.initVerify(pair.public)
    publicSignature.update(checkMessage.toByteArray(Charsets.UTF_8))
    val isCorrect: Boolean = publicSignature.verify(signature)

    if (isCorrect) {
        println("Подпись является подлинной")
    } else {
        println("Подпись не подлинная")
    }
}