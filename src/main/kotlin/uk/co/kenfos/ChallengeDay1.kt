package uk.co.kenfos

import kotlin.math.abs

typealias IntegerPair = Pair<Int, Int>
typealias IntegerColumn = List<Int>

fun calculateTotalDistance(input: List<IntegerPair>): Int {
    val (firstColumn, secondColumn) = splitToColumns(input).let { Pair(it.first.sorted(), it.second.sorted()) }

    return firstColumn.zip(secondColumn).sumOf { (first, second) -> abs(first - second) }
}

fun calculateSimilarityScore(input: List<IntegerPair>): Int {
    val (firstColumn, secondColumn) = splitToColumns(input)
    val secondColumnFrequencies = secondColumn.groupingBy { it }.eachCount()

    return firstColumn.sumOf { secondColumnFrequencies.getOrDefault(key = it, defaultValue = 0) * it }
}

private fun splitToColumns(input: List<IntegerPair>): Pair<IntegerColumn, IntegerColumn> {
    val initialValue = Pair<IntegerColumn, IntegerColumn>(emptyList(), emptyList())

    return input.fold(initialValue) { acc, (first, second) -> Pair(acc.first.plus(first), acc.second.plus(second)) }
}