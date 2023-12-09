import utils.InputReader

class Day1 {
    private val reader = InputReader("day1.txt")
    private lateinit var lines: MutableList<String>;
    private val numberString:List<String> = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    private fun getDigitsForLinePartOne(line: String): Int {
        val digits = line.filter { it.isDigit() }
        return if(digits.length == 1) {
            (digits + digits).toInt()
        } else {
            (digits.first().toString() + digits.last().toString()).toInt()
        }
    }

    private fun getDigitsForLinePartTwo(line: String): Int {
        // case only numbers
        if(isNumeric(line)) {
            return  getDigitsForLinePartOne((line))
        }
        var firstDigit: String = "";
        var secondDigit: String = "";
        var chars: String = "";
        // get first digit
        run breaking@ {
            line.forEach { it ->
                chars += it
                val  result = checkForSpelledDigitsOrDigits(it.toString(), chars)
                if(result.isNotEmpty()) {
                    firstDigit = result
                    chars = "";
                    return@breaking
                }
            }
        }
        // get second digit
        run breaking@ {
            line.reversed().forEach { it ->
                chars = it + chars
                val  result = checkForSpelledDigitsOrDigits(it.toString(), chars)
                if(result.isNotEmpty()) {
                    secondDigit = result
                    return@breaking
                }
            }
        }


      return (firstDigit + secondDigit).toInt()

    }

    private fun isNumeric(toCheck: String): Boolean {
        return toCheck.toIntOrNull() != null
    }
    private fun convertNumberStringToNumber(numberString: String): String {
        val wordToNumberMap = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )
        return  wordToNumberMap[numberString] ?: numberString
    }

    private fun checkForSpelledDigitsOrDigits(latestChar: String, charsTillNow: String): String {
        if(isNumeric(latestChar)) {
            return latestChar
        }
        val isSpelledNumber = numberString.find { charsTillNow.contains(it) }
        if(!isSpelledNumber.isNullOrEmpty()) {
            return convertNumberStringToNumber(isSpelledNumber);
        }
        return "";
    }

    private fun part1() {

        val digits: MutableList<Int> = mutableListOf()
        lines.forEach {it -> digits.add(getDigitsForLinePartOne(it))}
        println("Part1: The answer is ${digits.sum()}")
    }

    private fun part2() {
        val digits: MutableList<Int> = mutableListOf()
        lines.forEach {it -> digits.add(getDigitsForLinePartTwo(it))}
        println("Part2: The answer is ${digits.sum()}")
    }


    fun solve() {
        lines = reader.readInput()
        part1()
        part2()
    }
}

