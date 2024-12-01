package uk.co.kenfos

import kotlin.math.abs

fun calculateTotalDistance(input: List<List<Int>>): Int {
    val columns = splitLists(input)
    val firstColumn = columns.first().sorted()
    val secondColumn = columns.last().sorted()

    return firstColumn.zip(secondColumn).sumOf { pair -> abs(pair.first - pair.second) }
}

fun calculateSimilarityScore(input: List<List<Int>>): Int {
    val columns = splitLists(input)
    val firstColumn = columns.first()
    val secondColumn = columns.last()
    val secondColumnCount = countItems(secondColumn)

    return firstColumn.sumOf { (secondColumnCount[it] ?: 0) * it }
}

private fun splitLists(input: List<List<Int>>): List<List<Int>> {
    val initialValue = listOf<List<Int>>(emptyList(), emptyList())

    return input.fold(initialValue) { acc, (first, second) -> listOf(acc.first().plus(first), acc.last().plus(second)) }
}

private fun countItems(secondColumn: List<Int>): Map<Int, Int> {
    val initialValue = mutableMapOf<Int, Int>()

    return secondColumn.fold(initialValue) { acc, item ->
        acc[item] = acc.getOrDefault(item, 0) + 1
        acc
    }
}
