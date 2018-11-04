import java.io.File

calculateAndPrintMinPath()

fun calculateAndPrintMinPath() {
    // Convert file into a list of lists of integers for further processing
    var triangleList = readAndCovertFile(validateInputAndReturnFileName())

    var minPathList = ArrayList<Int>()
    calculateMinPathRecursively(minPathList, triangleList)

    println("Minimal path is: ${minPathList.displaySumAsString()} = ${minPathList.sum()}")

}

/**
 * Extended Arraylist function to display sum of each element individually using '+' operator after each element
 */
fun <T> ArrayList<T>.displaySumAsString(): String {
    var sb = StringBuilder()
    this.forEach {
        sb.append(it)
        if (this.indexOf(it) != this.size - 1) sb.append(" + ")
    }

    return sb.toString()
}

/**
 * Recursive methods that calculates minimu path of a triangle represented as list of lists, staring from the bottom.
 *
 * Reads last row and finds minimum, then for every pair of adjacent nodes of the min re-calculates recursively.
 *
 */
fun calculateMinPathRecursively(minPathList: ArrayList<Int>, triagleList: List<List<Int>>, adjacentNodes: Pair<Int, Int>? = null) {
    val currentRow = (if (adjacentNodes == null) triagleList.lastOrNull()
    else triagleList.lastOrNull()?.subList(adjacentNodes.first, adjacentNodes.second + 1)) ?: return

    val min = currentRow.min()
    val minIndex = currentRow.indexOf(min)

    if (min != null) {
        minPathList.add(min)
    }

    calculateMinPathRecursively(minPathList, triagleList.subList(0, triagleList.size - 1), if (minIndex > 0) Pair(minIndex - 1, minIndex) else Pair(0, 0))
}

/**
 * Reads file from disk using file path name given as input and returns triangle file as list of lists of integers
 * (every inner list represent a row of the triangle file)
 *
 * Throws exception in case non numeric character is detected in the file
 */
fun readAndCovertFile(fileName: String): List<List<Int>> {
    return try {
        File(fileName).useLines { it.toList() }.map { it.split(" ").map { it.toInt() }.toList() }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("Non numeric characters are allowed inside triangle.")
    }
}

/**
 * Checks if input argument is valid and returns file name to be parsed
 */
fun validateInputAndReturnFileName(): String {
    // Check if arguments are empty
    if (args.isEmpty()) throw IllegalArgumentException("No arguments passed.")

    // Check if fileName corresponds to a valid file
    val fileName = args[0]
    if (!File(fileName).exists()) throw IllegalArgumentException("No valid file name passed.")

    return fileName
}