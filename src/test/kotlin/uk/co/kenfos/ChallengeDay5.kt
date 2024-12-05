package uk.co.kenfos

import java.lang.System.lineSeparator
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class ChallengeDay5 {
    private val filePath = "src/test/resources/day5.txt"
    private val input = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    @Test
    fun `finds valid updates mid number sum`() {
        assertEquals(143, findValidUpdatesMidNumberSum(input))
    }

    @Test
    fun `finds valid updates mid number sum from file`() {
        val fileInput = Path(filePath).readLines().joinToString(lineSeparator())
        assertEquals(4609, findValidUpdatesMidNumberSum(fileInput))
    }

    @Test
    fun `finds invalid updates mid number sum`() {
        assertEquals(123, findInvalidUpdatesMidNumberSum(input))
    }

    @Test
    fun `finds invalid updates mid number sum from file`() {
        val fileInput = Path(filePath).readLines().joinToString(lineSeparator())
        assertEquals(5723, findInvalidUpdatesMidNumberSum(fileInput))
    }
}