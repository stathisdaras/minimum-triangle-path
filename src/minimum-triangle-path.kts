import java.io.File

calculateAndPrintMinPath()

fun calculateAndPrintMinPath() {
    // Convert file into a list of lists of integers for further processing
    var triangleList = readAndCovertFile(validateInputAndReturnFileName())

    // Calculate min path and save to list
    var minPathList = ArrayList<Int>()
    calculateMinPathRecursively(minPathList, triangleList)

    // Print min path calculating reverse display sum with condition that current element would not be the last
    var reveresedDisplaySum = minPathList.displaySumAsString { minPathList.indexOf(it) != minPathList.size - 1}.reversed()
    println("Minimal path is: ${reveresedDisplaySum} = ${minPathList.sum()}")

}

/**
 * Extended high order function inside ArrayList to display sum of each element individually using '+' operator after element if
 * condition is satisfied
 */
fun <T> ArrayList<T>.displaySumAsString(condition: (T) -> Boolean): String {
    var sb = StringBuilder()
    this.forEach {
        sb.append(it)
        if (condition(it)) sb.append(" + ")
    }

    return sb.toString()
}

/**
 * Recursive method that calculates minimum path of a triangle represented as list of lists, starting from the bottom.
 * Reads last row and finds minimum, then for every pair of adjacent nodes of the min re-calculates recursively.
 *
 */
fun calculateMinPathRecursively(minPathList: ArrayList<Int>, triagleList: List<List<Int>>, adjacentNodes: Pair<Int, Int>? = null) {
    // Reads last row if at the beginning, else the child adjacent nodes (bottom -> top)
    val currentRow = (if (adjacentNodes == null) triagleList.lastOrNull()
    else triagleList.lastOrNull()?.subList(adjacentNodes.first, adjacentNodes.second + 1)) ?: return

    // Calculates minimum node and adds to path
    val min = currentRow.min()
    val minIndex = currentRow.indexOf(min)
    if (min != null) {
        minPathList.add(min)
    }

    // Recursively invokes itsself cutting down last row of the triangle passing child adjacent nodes
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
        throw IllegalArgumentException("Only numeric characters are allowed inside triangle.")
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