package uk.co.kenfos

fun findCombinations(input: String, word: String): Int {
    val grid = Grid(input, searchRange = word.length - 1)
    val firstCharacter = word.first()

    fun countCombinations(rowIndex: Int, columnIndex: Int): Int {
        return grid
            .getCombinations(rowIndex, columnIndex)
            .map { it.joinToString("") }
            .count { it == word }
    }

    fun getRowMatches(row: CharArray, rowIndex: Int): List<Int> {
        return row.mapIndexed { columnIndex, character ->
            val validMatch = character == firstCharacter
            if (validMatch) countCombinations(rowIndex, columnIndex) else 0
        }
    }

    return grid.rows.foldIndexed(0) { rowIndex, acc, row -> acc + getRowMatches(row, rowIndex).sum() }
}

fun findXCombinations(input: String, word: String): Int {
    val grid = Grid(input, searchRange = word.length - 1)
    val firstCharacter = word.first()
    val lastCharacter = word.last()
    val xCombinationCount = 2

    fun countCombinations(rowIndex: Int, columnIndex: Int): Int {
        return grid
            .getXCombinations(rowIndex, columnIndex)
            .map { it.joinToString("") }
            .count { it == word }
            .let { if (it == xCombinationCount) 1 else 0 }
    }

    fun getRowMatches(row: CharArray, rowIndex: Int): List<Int> {
        return row.mapIndexed { columnIndex, character ->
            val validMatch = character == firstCharacter || character == lastCharacter
            if (validMatch) countCombinations(rowIndex, columnIndex) else 0
        }
    }

    return grid.rows.foldIndexed(0) { rowIndex, acc, row -> acc + getRowMatches(row, rowIndex).sum() }
}

private data class Grid(val rows: List<CharArray>, val searchRange: Int) {
    constructor(input: String, searchRange: Int) : this(input.lines().map { it.toCharArray() }, searchRange)

    fun getCombinations(row: Int, column: Int) = listOf(
        getEastLine(row, column),
        getWestLine(row, column),
        getSouthLine(row, column),
        getNorthLine(row, column),
        getSouthEastLine(row, column),
        getSouthWestLine(row, column),
        getNorthEastLine(row, column),
        getNorthWestLine(row, column)
    )

    fun getXCombinations(row: Int, column: Int) = listOf(
        getSouthEastLine(row, column),
        getNorthEastLine(row + searchRange, column),
        getSouthWestLine(row, column + searchRange),
        getNorthWestLine(row + searchRange, column + searchRange)
    )

    private fun getEastLine(row: Int, column: Int) = getLine { row to column + it }
    private fun getWestLine(row: Int, column: Int) = getLine { row to column - it }
    private fun getSouthLine(row: Int, column: Int) = getLine { row + it to column }
    private fun getNorthLine(row: Int, column: Int) = getLine { row - it to column }
    private fun getSouthEastLine(row: Int, column: Int) = getLine { row + it to column + it }
    private fun getSouthWestLine(row: Int, column: Int) = getLine { row + it to column - it }
    private fun getNorthEastLine(row: Int, column: Int) = getLine { row - it to column + it }
    private fun getNorthWestLine(row: Int, column: Int) = getLine { row - it to column - it }

    private fun getLine(line: (index: Int) -> Pair<Int, Int>): List<Char> {
        return (0..searchRange).mapNotNull { line(it).let { (row, column) -> rows.getOrNull(row)?.getOrNull(column) } }
    }
}