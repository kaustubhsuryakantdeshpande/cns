
fun main() {
    println("Enter input to encrypt the text")
    val enteredString = "KaustubhAndKrishna"
    val result = caesarCipherEncrypt(enteredString)
    println("The encrypted text is - $result")
}

fun caesarCipherEncrypt(stringToEncrypt: String): String {
    var encryptedString = ""
    for (char in stringToEncrypt) {
        val encryptedChar = when (char) {
            in 'a'..'z' -> {
                val shiftedSmall = ((char - 'a' + 3) % 26) + 'a'.toInt()
                shiftedSmall.toChar()
            }
            in 'A'..'Z' -> {
                val shiftedLarge = ((char - 'A' + 3) % 26) + 'A'.toInt()
                shiftedLarge.toChar()
            }
            else -> char
        }
        encryptedString += encryptedChar
    }
    return encryptedString
}