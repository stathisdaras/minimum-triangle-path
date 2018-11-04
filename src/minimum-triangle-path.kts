import java.io.File

parseAndPrintTriangleFile()

fun parseAndPrintTriangleFile() {
    val fileName = "triangle.txt"
    File(fileName).useLines { it.toList() }.map { println(it); it.split(" ").map { it.toInt() }.toList() }

}


