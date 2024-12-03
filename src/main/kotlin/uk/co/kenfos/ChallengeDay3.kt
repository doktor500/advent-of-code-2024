package uk.co.kenfos

private val instructionRegex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
private val ignoredInstructionRegex = """don't\(\)(.*?)do\(\)""".toRegex()
private val numbersRegex = """\d+""".toRegex()

fun calculateMultiplicationInstructions(input: String, includeConditionalStatements: Boolean = false): Int {
    val allInstructionsResult = calculateMultiplications(input)
    return when {
        includeConditionalStatements -> {
            val ignoredInstructions = ignoredInstructionRegex.findAll(input)
            val ignoredInstructionsResult = ignoredInstructions.sumOf { calculateMultiplications(it.value) }
            allInstructionsResult - ignoredInstructionsResult
        }
        else -> allInstructionsResult
    }
}

private fun calculateMultiplications(input: String): Int {
    return instructionRegex.findAll(input).sumOf {
        val numbers = numbersRegex.findAll(it.value)
        numbers.first().value.toInt() * numbers.last().value.toInt()
    }
}