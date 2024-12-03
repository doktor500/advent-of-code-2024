package uk.co.kenfos

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class ChallengeDay3Test {
    private val filePath = "src/test/resources/day3.txt"

    private val input1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    private val input2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    private val input3 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))do()?mul(8,5))"

    @Test
    fun `calculates multiplication instructions`() {
        assertEquals(161, calculateMultiplicationInstructions(input1))
    }

    @Test
    fun `calculates multiplication instructions from file input`() {
        val fileInput = Path(filePath).readLines().joinToString()
        assertEquals(190604937, calculateMultiplicationInstructions(fileInput))
    }

    @Test
    fun `calculates multiplication instructions with conditional statements`() {
        assertEquals(48, calculateMultiplicationInstructions(input2, includeConditionalStatements = true))
    }

    @Test
    fun `calculates multiplication instructions with conditional statements and a final don't statement`() {
        assertEquals(88, calculateMultiplicationInstructions(input3, includeConditionalStatements = true))
    }

    @Test
    fun `calculates multiplication instructions with conditional statements from file input`() {
        val fileInput = Path(filePath).readLines().joinToString()
        assertEquals(82857512, calculateMultiplicationInstructions(fileInput, includeConditionalStatements = true))
    }
}