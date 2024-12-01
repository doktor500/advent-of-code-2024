package uk.co.kenfos

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class ChallengeDay1Test {
    private val whitespace = "\\s+".toRegex()

    private val input = listOf(
        listOf(3, 4),
        listOf(4, 3),
        listOf(2, 5),
        listOf(1, 3),
        listOf(3, 9),
        listOf(3, 3),
    )

    @Test
    fun `calculates the total distance`() {
        assertEquals(11, calculateTotalDistance(input))
    }

    @Test
    fun `calculates the total distance from file input`() {
        assertEquals(1258579, calculateTotalDistance(getFileContent()))
    }

    @Test
    fun `calculates similarity score`() {
        assertEquals(31, calculateSimilarityScore(input))
    }

    @Test
    fun `calculates similarity score from file input`() {
        assertEquals(23981443, calculateSimilarityScore(getFileContent()))
    }

    private fun getFileContent(): List<List<Int>> {
        return Path("src/test/resources/day1.txt").readLines().map { line -> line.split(whitespace).map { it.toInt() } }
    }
}