package converter

fun main() {
    val converter = Converter()
    converter.start()
}

class Converter {
    private var isRunning = true

    private var numberDouble: Double = 0.0
    private lateinit var fromUnit: UnitBase
    private lateinit var toUnit: UnitBase

    fun start() {
        do {
            processInput()
        } while (isRunning)
    }

    private fun processInput() {
        print("Enter what you want to convert (or exit): ")
        try {
            val input = readln().split(" ")
            if (input[0] == "exit") {
                isRunning = false
                return
            }

            numberDouble = input[0].toDouble()
            val fromUnitInput = input[1]
            val toUnitInput = input[3]

            fromUnit = getUnitOfMeasure(fromUnitInput)
            toUnit = getUnitOfMeasure(toUnitInput)
            verifyInputData(fromUnit, toUnit)

            convertAndPrintOutput(numberDouble, fromUnit, toUnit)

        } catch (ex: Exception) {
            println(ex.message)
        }
    }
}

private fun verifyInputData(unitOfMeasureFrom: UnitBase, unitOfMeasureTo: UnitBase) {
    if (unitOfMeasureFrom == UnitBase.BLANK || unitOfMeasureTo == UnitBase.BLANK) {
        val fromTxt = if (unitOfMeasureFrom.type == '0') "???" else unitOfMeasureFrom.pluralName
        val toTxt = if (unitOfMeasureTo.type == '0') "???" else unitOfMeasureTo.pluralName
        throw Exception("Conversion from $fromTxt to $toTxt is impossible\n")
    }

    if (unitOfMeasureFrom.type != unitOfMeasureTo.type) {
        throw Exception("Conversion from ${unitOfMeasureFrom.pluralName} to ${unitOfMeasureTo.pluralName} is impossible\n")
    }
}

private fun convertAndPrintOutput(numberDouble: Double, unitOfMeasureFrom: UnitBase, unitOfMeasureTo: UnitBase) {
    val fromTxt = if (numberDouble != 1.0) unitOfMeasureFrom.pluralName else unitOfMeasureFrom.singularName
    val outputNum = numberDouble * unitOfMeasureFrom.multiplayer / unitOfMeasureTo.multiplayer
    val toTxt = if (outputNum != 1.0) unitOfMeasureTo.pluralName else unitOfMeasureTo.singularName
    println("$numberDouble $fromTxt is $outputNum $toTxt\n")
}

private fun getUnitOfMeasure(umString: String): UnitBase {
    for (unit in UnitBase.entries) {
        if (umString.lowercase().matches(unit.pattern)) {
            return unit
        }
    }
    return UnitBase.BLANK
}
