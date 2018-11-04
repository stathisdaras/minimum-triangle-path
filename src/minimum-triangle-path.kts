import java.io.File

calculateAndPrintMinPath()

fun calculateAndPrintMinPath() {
    // Convert file into a list of lists of integers for further processing
    var triangleList = readAndCovertFile(validateInputAndReturnFileName())
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
    if(!File(fileName).exists()) throw IllegalArgumentException("No valid file name passed.")

    return fileName
}