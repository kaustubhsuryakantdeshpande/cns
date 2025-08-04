// Tutorial 4 CNS

import kotlin.math.pow

fun main() {
    println("Diffie Hellman Key Exchange Algorithm")
    println()

    // Getting the parameters from the user
    //p
    println("Enter prime number p:")
    val p = readLine()?.toIntOrNull() ?: run {
        println("Invalid input. Please enter a valid prime number.")
        23
    }
    //g
    print("Enter generator g:")
    val g = readLine()?.toIntOrNull() ?: run {
        println("Invalid input. Please enter a valid generator.")
        5
    }
    //validate if g is a validate generator
    if(g >= p) {
        println("Warning: Generator should be less than prime number!")
    }

    println("\nPublic parameters:")
    println("Prime p = $p")
    println("Generator g = $g")
    println()

    // Alice's private key
    println("Alice's private key (a):")
    val a = readLine()?.toIntOrNull() ?: run {
        println("Invalid input. Please enter a valid private key.")
        6  
    }

    // Get Bob's private key
    println("Bob's private key (b):")
    val b = readLine()?.toIntOrNull() ?: run {
        println("Invalid input. Please enter a valid private key.")
        15
    }

    // validate private keys
    if (a >= p || b >= p ) {
        println("Warning: Private keys should be less than prime number.")
    }

    println("\nPrivate keys:")
    println("Alice's private key (a) = $a")
    println("Bob's private key (b) = $b")
    println()

    // Calculate public keys
    println("Computing Public keys.....")
    val alicePublicKey = modularExponentiation(g, a, p)
    println("Alice's public key A = g ^ a mod p = $g ^ $a mod $p = $alicePublicKey")

    val bobPublicKey = modularExponentiation(g, b, p)
    println("Bob's public key B = g  ^ b mod p = $g ^ $b mod $p = $bobPublicKey")

    println("\nPublic keys exchanged over insecure channel:")
    println("Alice's public key A = $alicePublicKey to Bob")
    println("Bob's public key B = $bobPublicKey to Alice")

    // Calculate shared secret keys
    println("\nComputing shared secret keys.....")
    val sharedSecretAlice = modularExponentiation(bobPublicKey, a, p)
    println("Alice computes: K = B^a mod p = $bobPublicKey^$a mod $p = $sharedSecretAlice")

    val sharedSecretBob = modularExponentiation(alicePublicKey, b, p)
    println("Bob computes: K = A^b mod p = $alicePublicKey^$b mod $p = $sharedSecretBob")

    // Verify both computed the same shared secret
    println("\nVerification:")
    if (sharedSecretAlice == sharedSecretBob) {
        println("✓ SUCCESS: Both parties computed the same shared secret!")
        println("Shared Secret Key = $sharedSecretAlice")
    } else {
        println("✗ ERROR: Different shared secrets computed!")
        println("Alice got: $sharedSecretAlice, Bob got: $sharedSecretBob")
    }
}

/**
 * Computes (base^exponent) mod modulus using modular exponentiation
 * This prevents integer overflow for large numbers
 */
fun modularExponentiation(base: Int, exponent: Int, modulus: Int): Int {
    if (modulus == 1) return 0
    
    var result = 1
    var baseVar = base % modulus
    var expVar = exponent
    
    while (expVar > 0) {
        // If exponent is odd, multiply base with result
        if (expVar % 2 == 1) {
            result = (result * baseVar) % modulus
        }
        
        // Now exponent must be even
        expVar = expVar shr 1  // Divide by 2
        baseVar = (baseVar * baseVar) % modulus
    }
    
    return result
}