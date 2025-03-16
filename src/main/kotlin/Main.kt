package converter

fun main() {
    print("Enter a number and a measure of length: ")
    val amount: Double
    var inputUnit = ""

    try {
        val input = readln().split(" ")
        amount = input[0].toDouble()
        inputUnit = input[1].lowercase().trim()

        processConversion(inputUnit, amount)
    } catch (ex: Exception) {
        println("Wrong input. Unknown unit $inputUnit")
    }
}

private fun processConversion(inputUnit: String, amount: Double) {
    for (unit in Unit.entries) {
        if (unit.pattern.matches(inputUnit.lowercase())) {
            println(convertInput(amount, unit))
            return
        }
    }
    throw IllegalArgumentException("Conversion from $inputUnit to meters is not supported")
}

fun convertInput (amount: Double, initialUnit: Unit): String {
    val result =  initialUnit.amountInMeter * amount
    val initialUnitName = if (amount != 1.0) initialUnit.pluralName else initialUnit.singularName
    val targetUnitName = "meter" + if (result != 1.0) "s" else ""
    return "$amount $initialUnitName is $result $targetUnitName"
}
