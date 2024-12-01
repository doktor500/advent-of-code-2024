package uk.co.kenfos

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class ChallengeDay1Test {
    private val filePath = "src/test/resources/day1.txt"
    private val whitespace = """\s+""".toRegex()

    private val input = listOf(
        Pair(3, 4),
        Pair(4, 3),
        Pair(2, 5),
        Pair(1, 3),
        Pair(3, 9),
        Pair(3, 3),
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

    private fun getFileContent(): List<Pair<Int, Int>> {
        return Path(filePath)
            .readLines()
            .map { line -> line.split(whitespace).let { Pair(it.first().toInt(), it.last().toInt()) } }
    }
}