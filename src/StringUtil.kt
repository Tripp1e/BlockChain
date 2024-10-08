import java.security.MessageDigest

object StringUtil {
    fun applySha256(input: String): String {
        try {

            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(input.toByteArray(Charsets.UTF_8))
            val hexString = StringBuffer()

            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }

            return hexString.toString()

        }

        catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}