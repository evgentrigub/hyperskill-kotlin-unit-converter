package org.example

fun main() {
    val itemsMap = getItems()
    showEarnings(itemsMap)

    val totalIncome = getIncome(itemsMap)
    val (staffExpenses, otherExpenses) = getExpenses()
    showNetIncome(totalIncome, staffExpenses, otherExpenses)
}

private fun showNetIncome(totalIncome: Int, staffExpenses: Int, otherExpenses: Int) {
    val netIncome = totalIncome - (staffExpenses + otherExpenses)
    println("Net income: \$${netIncome}")
}

private fun getExpenses(): Pair<Int, Int> {
    println("Staff expenses:")
    val staffExpenses = readln().toInt()
    println("Other expenses:")
    val otherExpenses = readln().toInt()
    return Pair(staffExpenses, otherExpenses)
}

private fun getIncome(itemsMap: Map<String, Int>): Int {
    val totalIncome = itemsMap.values.sum();
    println("\nIncome: \$${totalIncome}")
    return totalIncome
}

private fun showEarnings(itemsMap: Map<String, Int>) {
    println("Earned amount:")
    itemsMap.forEach { item, amount ->
        println("$item: \$$amount")
    }
}

private fun getItems() = mapOf(
    "Bubblegum" to 202,
    "Toffee" to 118,
    "Ice cream" to 2250,
    "Milk chocolate" to 1680,
    "Doughnut" to 1075,
    "Pancake" to 80,
)