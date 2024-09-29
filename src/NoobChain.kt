import com.google.gson.GsonBuilder

val primChain = ArrayList<String>()
val noobChain = ArrayList<Block>()
const val difficulty = 5

fun main() {

    noobChain.add(Block("Hi, Im the first block", "0"))
    println("Trying to mine genesis block...")
    noobChain[0].mineBlock(difficulty)

    primChain.add("Yo, Im the second block")
    primChain.add("Oy, Im the third block")

    for (string in primChain) {
        noobChain.add(Block(string, noobChain.last().hash))
        println("Trying to mine block...")
        noobChain.last().mineBlock(difficulty)
    }

    println("\nThe blockchain is valid: ${isChainValid()}")

    val noobChainJson = GsonBuilder().setPrettyPrinting().create().toJson(noobChain)
    println("\nThe blockchain: ")
    println(noobChainJson)

}

fun isChainValid(): Boolean {
    var currentBlock: Block?
    var previousBlock: Block?
    val hashTarget = "0".repeat(difficulty)

    for(i in 1..<noobChain.size) {
        currentBlock = noobChain[i]
        previousBlock = noobChain[i-1]

        if(currentBlock.hash != currentBlock.calculateHash()){
            println("Current hashes not equal")
            return false
        }

        if(previousBlock.hash != currentBlock.previousHash) {
            println("Previous hashes not equal")
            println("Current Hash: " + currentBlock.previousHash)
            println("Previous Hash: " + previousBlock.hash)
            return false
        }

        if(currentBlock.hash.substring(0, difficulty) != hashTarget) {
            println("This block hasn't been mined")
            return false
        }

    }
    return true
}
