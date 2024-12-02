package uk.co.kenfos

import kotlin.math.abs

private const val SEPARATOR = " "

data class ProblemDampener(val active: Boolean = false)

fun countSafeReports(input: String, problemDampener: ProblemDampener = ProblemDampener()): Long {
    return input.lines().sumOf { line ->
        if (isSafe(line)) 1L
        else if (problemDampener.active && isSafeWithProblemDampener(line)) 1L
        else 0L
    }
}

private fun isSafe(line: String): Boolean {
    val numbers = line.split(SEPARATOR).map { it.toInt() }
    val sortedAscending = numbers.sorted().joinToString(separator = SEPARATOR) == line
    val sortedDescending = numbers.sorted().reversed().joinToString(separator = SEPARATOR) == line
    val numbersAreSorted = sortedAscending || sortedDescending
    val validDifference = numbers.zipWithNext().all { abs(it.first - it.second) in 1..3 }

    return numbersAreSorted && validDifference
}

private fun isSafeWithProblemDampener(line: String): Boolean {
    val numbers = line.split(SEPARATOR).map { it.toInt() }
    val combinations = numbers.indices.map { numbers.removeAtIndex(it) }

    return combinations.any { isSafe(it.joinToString(separator = SEPARATOR)) }
}

private fun <T> List<T>.removeAtIndex(index: Int): List<T> = this.filterIndexed { i, _ -> i != index }