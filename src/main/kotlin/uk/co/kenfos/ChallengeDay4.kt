package uk.co.kenfos

fun findCombinations(input: String, word: String): Int {
    val grid = Grid(input, word.length - 1)
    val firstCharacter = word.first()

    fun countCombinations(rowIndex: Int, columnIndex: Int): Int {
        return grid.getCombinations(rowIndex, columnIndex).map { it.joinToString("") }.count { it == word }
    }

    fun getRowMatches(row: CharArray, rowIndex: Int): List<Int> {
        return row.mapIndexed { columnIndex, character ->
            if (character == firstCharacter) countCombinations(rowIndex, columnIndex) else 0
        }
    }

    return grid.rows.foldIndexed(0) { rowIndex, acc, row -> acc + getRowMatches(row, rowIndex).sum() }
}

fun findXCombinations(input: String, word: String): Int {
    val grid = Grid(input, word.length - 1)
    val firstCharacter = word.first()
    val lastCharacter = word.last()
    val xCombinationCount = 2

    fun countValidCombinations(rowIndex: Int, columnIndex: Int): Int {
        val combinations = grid.getXCombinations(rowIndex, columnIndex).map { it.joinToString("") }.count { it == word }
        return if (combinations == xCombinationCount) 1 else 0
    }

    fun getRowMatches(row: CharArray, rowIndex: Int): List<Int> {
        return row.mapIndexed { columnIndex, character ->
            val characterMatch = character == firstCharacter || character == lastCharacter
            if (characterMatch) countValidCombinations(rowIndex, columnIndex) else 0
        }
    }

    return grid.rows.foldIndexed(0) { rowIndex, acc, row -> acc + getRowMatches(row, rowIndex).sum() }
}

private data class Grid(val rows: List<CharArray>, val searchRange: Int) {
    constructor(input: String, searchRange: Int) : this(input.lines().map { it.toCharArray() }, searchRange)

    fun getCombinations(row: Int, column: Int): List<List<Char>> {
        return listOf(
            getHorizontal(row, column),
            getBackwardHorizontal(row, column),
            getVertical(row, column),
            getBackwardVertical(row, column),
            getRightDiagonal(row, column),
            getBackwardRightDiagonal(row, column),
            getLeftDiagonal(row, column),
            getBackwardLeftDiagonal(row, column)
        )
    }

    fun getXCombinations(row: Int, column: Int): List<List<Char>> {
        return listOf(
            getRightDiagonal(row, column),
            getBackwardRightDiagonal(row, column + searchRange).reversed(),
            getLeftDiagonal(row + searchRange, column).reversed(),
            getBackwardLeftDiagonal(row + searchRange, column + searchRange)
        )
    }

    private fun getHorizontal(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows[row].getOrNull(column + it) }
    }

    private fun getBackwardHorizontal(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows[row].getOrNull(column - it) }
    }

    private fun getVertical(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows.getOrNull(row + it)?.get(column) }
    }

    private fun getBackwardVertical(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows.getOrNull(row - it)?.get(column) }
    }

    private fun getRightDiagonal(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows.getOrNull(row + it)?.getOrNull(column + it) }
    }

    private fun getBackwardRightDiagonal(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows.getOrNull(row + it)?.getOrNull(column - it) }
    }

    private fun getLeftDiagonal(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows.getOrNull(row - it)?.getOrNull(column + it) }
    }

    private fun getBackwardLeftDiagonal(row: Int, column: Int): List<Char> {
        return (0..searchRange).mapNotNull { rows.getOrNull(row - it)?.getOrNull(column - it) }
    }
}