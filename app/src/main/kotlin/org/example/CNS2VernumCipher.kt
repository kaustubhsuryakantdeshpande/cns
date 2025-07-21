fun main() {
    println("Enter the input key: ")
//     val key = readLine() ?: ""
val key = "rahul"
    println("Enter plain text: ")
//     val plainText = readLine() ?: ""
val plainText = "hello"

    // Repeat the key to match the length of the plaintext
    val extendedKey = key.repeat((plainText.length + key.length - 1) / key.length)

    val encryptedText = vernumEncrypt(plainText, extendedKey)
    println("Encrypted text: $encryptedText")

    val decryptedText = vernumDecrypt(encryptedText, extendedKey)
    println("Decrypted text: $decryptedText")
}

fun vernumEncrypt(plainText: String, key: String): String {
    return plainText.zip(key) { p, k ->
        val encryptedChar = (p.code xor k.code) % 95 + 32 // Ensures printable ASCII
        encryptedChar.toChar()
    }.joinToString("")
}

fun vernumDecrypt(encryptedText: String, key: String): String {
    return encryptedText.zip(key) { e, k ->
        val decryptedChar = (e.code xor k.code) % 95 + 32 // Ensures printable ASCII
        decryptedChar.toChar()
    }.joinToString("")
}
