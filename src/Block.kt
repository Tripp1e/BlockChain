import java.util.Date

class Block(
    val data: String,
    val previousHash: String,
    var hash: String = "",
    private val timestamp: Long = Date().time,
    private var nonce: Int = 0
) {

    init {
        hash = calculateHash()
    }

    fun calculateHash(): String {
        val calculatedHash = StringUtil.applySha256(
            previousHash +
            timestamp.toString() +
            nonce.toString() +
            data
        )
        return calculatedHash
    }

    fun mineBlock(difficulty: Int) {
        val target = "0".repeat(difficulty)
        while (hash.substring(0, difficulty) != target) {
            nonce++
            hash = calculateHash()
        }
        println("Block mined!! : $hash")
    }

}