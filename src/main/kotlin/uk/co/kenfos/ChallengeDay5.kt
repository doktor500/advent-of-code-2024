package uk.co.kenfos

private const val PIPE_SEPARATOR = "|"
private const val COMMA_SEPARATOR = ","

private data class Data(val pages: List<Pair<Int, Int>>, val updates: List<List<Int>>)

fun findValidUpdatesMidNumberSum(input: String): Int {
    val data = parseInput(input.lines())
    val validUpdates = getValidUpdates(data)

    return validUpdates.sumOf { it[it.size / 2] }
}

fun findInvalidUpdatesMidNumberSum(input: String): Int {
    val data = parseInput(input.lines())
    val groupedPages = toGroupedPages(data)
    val invalidUpdates = data.updates.filter { update -> !isValidUpdate(groupedPages, update) }
    val newValidUpdates = invalidUpdates.map { update -> orderInvalidUpdate(groupedPages, update) }

    return newValidUpdates.sumOf { it[it.size / 2] }
}

private fun getValidUpdates(data: Data): List<List<Int>> {
    return data.updates.filter { update -> isValidUpdate(toGroupedPages(data), update) }
}

private fun isValidUpdate(groupedPages: Map<Int, Set<Int>>, update: List<Int>): Boolean {
    return update.withIndex().all { (index, page) ->
        val currentPageTrailingPages = groupedPages.getOrDefault(page, emptyList())
        val remainingPages = update.subList(index + 1, update.size)
        remainingPages.all { currentPageTrailingPages.contains(it) }
    }
}

private fun toGroupedPages(data: Data): Map<Int, Set<Int>> {
    val emptyGroupedPages = emptyMap<Int, Set<Int>>()
    return data.pages.fold(emptyGroupedPages) { acc, page ->
        val currentGroup = acc.getOrDefault(page.first, emptySet())
        acc.plus(page.first to currentGroup.plus(page.second))
    }
}

private fun orderInvalidUpdate(groupedPages: Map<Int, Set<Int>>, update: List<Int>): List<Int> {
    return when (val page = findNextPage(groupedPages, update)) {
        null -> emptyList()
        else -> listOf(page).plus(orderInvalidUpdate(groupedPages.minus(page), update.minus(page)))
    }
}

private fun findNextPage(groupedPages: Map<Int, Set<Int>>, update: List<Int>): Int? {
    return update.find {
        val remainingPages = update.minus(it)
        val trailingPages = groupedPages.getOrDefault(it, emptySet())
        remainingPages.all { page -> trailingPages.contains(page) }
    }
}

private fun parseInput(lines: List<String>): Data {
    val initialData = Data(pages = emptyList(), updates = emptyList())
    return lines.fold(initialData) { acc, line ->
        when {
            line.contains(PIPE_SEPARATOR) -> addLine(acc, line)
            line.contains(COMMA_SEPARATOR) -> addUpdate(acc, line)
            else -> acc
        }
    }
}

private fun addUpdate(acc: Data, line: String): Data {
    val update = line.split(COMMA_SEPARATOR).map { it.toInt() }
    return Data(acc.pages, acc.updates.plusElement(update))
}

private fun addLine(acc: Data, line: String): Data {
    val page = line.split(PIPE_SEPARATOR).map { it.toInt() }.let { it.first() to it.last() }
    return Data(acc.pages.plus(page), acc.updates)
}