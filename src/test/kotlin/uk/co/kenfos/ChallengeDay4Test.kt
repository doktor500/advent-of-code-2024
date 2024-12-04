package uk.co.kenfos

import java.lang.System.lineSeparator
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class ChallengeDay4Test {
    private val filePath = "src/test/resources/day4.txt"
    private val xmasInput = """
        ....XXMAS.
        .SAMXMS...
        ...S..A...
        ..A.A.MS.X
        XMASAMX.MM
        X.....XA.A
        S.S.S.S.SS
        .A.A.A.A.A
        ..M.M.M.MM
        .X.X.XMASX
    """.trimIndent()

    private val masInput = """
        .M.S......
        ..A..MSMS.
        .M.S.MAA..
        ..A.ASMSM.
        .M.S.M....
        ..........
        S.S.S.S.S.
        .A.A.A.A..
        M.M.M.M.M.
        ..........
    """.trimIndent()

    @Test
    fun `finds xmas words`() {
        assertEquals(18, findCombinations(xmasInput, word = "XMAS"))
    }

    @Test
    fun `finds xmas words from file input`() {
        val fileInput = Path(filePath).readLines().joinToString(lineSeparator())
        assertEquals(2593, findCombinations(fileInput, word = "XMAS"))
    }

    @Test
    fun `finds x combinations`() {
        assertEquals(9, findXCombinations(masInput, word = "MAS"))
    }

    @Test
    fun `finds x combinations from file input`() {
        val fileInput = Path(filePath).readLines().joinToString(lineSeparator())
        assertEquals(1950, findXCombinations(fileInput, word = "MAS"))
    }
}
