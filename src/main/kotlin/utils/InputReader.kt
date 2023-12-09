package utils

class InputReader internal constructor(private val filename: String) {

    fun readInput(): MutableList<String> {
        val input: MutableList<String> = mutableListOf()

        try {
            val inputStream = InputReader::class.java.getResourceAsStream("/$filename")

            if (inputStream != null) {
                inputStream.bufferedReader(Charsets.UTF_8).useLines { lines ->
                    lines.forEach { line ->
                        input += line
                    }
                }
            } else {
                println("File not found: $filename")
            }
        } catch (e: Exception) {
            println("Error reading the file: $e")
        }

        return input
    }
}
