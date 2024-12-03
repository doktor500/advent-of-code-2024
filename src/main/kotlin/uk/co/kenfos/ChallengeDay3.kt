package uk.co.kenfos

val instructionRegex = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex()
val ignoredInstructionRegex = """don't\(\)(.*?)do\(\)""".toRegex()
val numbersRegex = """\d+""".toRegex()

fun calculateMultiplicationInstructions(input: String, conditionalStatements: Boolean = false): Int {
    val allInstructionsResult = calculateMultiplications(input)
    if (!conditionalStatements) return allInstructionsResult

    val ignoredInstructions = ignoredInstructionRegex.findAll(input)
    val invalidInstructionsResult = ignoredInstructions.sumOf { calculateMultiplications(it.value) }
    return allInstructionsResult - invalidInstructionsResult
}

private fun calculateMultiplications(input: String): Int {
    return instructionRegex.findAll(input).sumOf {
        val numbers = numbersRegex.findAll(it.value)
        numbers.first().value.toInt() * numbers.last().value.toInt()
    }
}