package uk.co.kenfos

import java.lang.System.*
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class ChallengeDay2Test {
    private val filePath = "src/test/resources/day2.txt"

    private val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Test
    fun `calculates how many reports are safe`() {
        assertEquals(2, countSafeReports(input))
    }

    @Test
    fun `calculates how many reports are safe from file input`() {
        val input = Path(filePath).readLines().joinToString(separator = lineSeparator())
        assertEquals(282, countSafeReports(input))
    }

    @Test
    fun `calculates how many reports are safe when problem dampener is active`() {
        assertEquals(4, countSafeReports(input, problemDampener = true))
    }

    @Test
    fun `calculates how many reports are safe from file input when problem dampener is active`() {
        val input = Path(filePath).readLines().joinToString(separator = lineSeparator())
        assertEquals(349, countSafeReports(input, problemDampener = true))
    }
}