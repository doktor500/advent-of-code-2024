package uk.co.kenfos

import kotlin.math.abs

private const val SEPARATOR = " "
private val VALID_DIFF_RANGE = 1..3

fun countSafeReports(input: String, problemDampener: Boolean = false): Long {
    return input.lines().sumOf { reportLine ->
        when {
            problemDampener && isSafeReportWithProblemDampener(reportLine) -> 1L
            isSafeReport(reportLine) -> 1L
            else -> 0L
        }
    }
}

private fun isSafeReportWithProblemDampener(reportLine: String): Boolean {
    val numbers = splitToNumbers(reportLine)
    val combinations = numbers.indices.map { numbers.remoteItemAtIndex(it) }

    return combinations.any { isSafeReport(it.joinToString(separator = SEPARATOR)) }
}

private fun isSafeReport(reportLine: String): Boolean {
    val numbers = splitToNumbers(reportLine)
    return containsValidSorting(numbers, reportLine) && containsValidDifference(numbers)
}

private fun splitToNumbers(reportLine: String): List<Int> {
    return reportLine.split(SEPARATOR).map { it.toInt() }
}

private fun containsValidSorting(numbers: List<Int>, reportLine: String): Boolean {
    val sortedNumbers = numbers.sorted()
    val sortedAscending = sortedNumbers.joinToString(separator = SEPARATOR) == reportLine
    val sortedDescending = sortedNumbers.reversed().joinToString(separator = SEPARATOR) == reportLine

    return  sortedAscending || sortedDescending
}

private fun containsValidDifference(numbers: List<Int>): Boolean {
    return numbers.zipWithNext().all { abs(it.first - it.second) in VALID_DIFF_RANGE }
}

private fun <T> List<T>.remoteItemAtIndex(index: Int): List<T> = this.filterIndexed { i, _ -> i != index }