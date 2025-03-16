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
            val inputString = readln().split(" ")
            if (inputString[0] == "exit") {
                isRunning = false
                return
            }

            setInputData(inputString)
            verifyInputData(fromUnit, toUnit)
            convertAndPrintOutput(numberDouble, fromUnit, toUnit)

        } catch (ex: IllegalArgumentException) {
            println(ex.message)
        } catch (ex: Exception) {
            println("Parse error\n")
        }
    }

    private fun setInputData(input: List<String>) {
        try {
            when (input.size) {
                4 -> {
                    fromUnit = getUnitOfMeasure(input[1])
                    toUnit = getUnitOfMeasure(input[3])
                }
                5 -> {
                    if (checkIfDegree(input[1])){
                        fromUnit = getUnitOfMeasure("${input[1]} ${input[2]}")
                        toUnit = getUnitOfMeasure(input[4])
                    } else if (checkIfDegree(input[3])){
                        fromUnit = getUnitOfMeasure(input[1])
                        toUnit = getUnitOfMeasure("${input[3]} ${input[4]}")
                    }
                }
                6 -> {
                    if (!checkIfDegree(input[1]) || !checkIfDegree(input[4])) throw Exception()
                    fromUnit = getUnitOfMeasure("${input[1]} ${input[2]}")
                    toUnit = getUnitOfMeasure("${input[4]} ${input[5]}")
                }
                else -> throw Exception()
            }

            numberDouble = input[0].toDouble()
            if(numberDouble < 0 && (fromUnit.type == 'L' || fromUnit.type == 'W')) {
                val unit = if (fromUnit.type == 'L') "Length" else "Weight"
                throw IllegalArgumentException("$unit shouldn't be negative\n")
            }
        } catch (ex: NumberFormatException) {
            throw IllegalArgumentException("Parse error\n")
        }
    }

    private fun checkIfDegree(txt : String) : Boolean {
        return txt.lowercase().contains("degree")
    }

    private fun verifyInputData(unitOfMeasureFrom: UnitBase, unitOfMeasureTo: UnitBase) {
        if (unitOfMeasureFrom == UnitBase.BLANK || unitOfMeasureTo == UnitBase.BLANK) {
            val fromTxt = if (unitOfMeasureFrom.type == '0') "???" else unitOfMeasureFrom.pluralName
            val toTxt = if (unitOfMeasureTo.type == '0') "???" else unitOfMeasureTo.pluralName
            throw IllegalArgumentException("Conversion from $fromTxt to $toTxt is impossible\n")
        }

        if (unitOfMeasureFrom.type != unitOfMeasureTo.type) {
            val message = "Conversion from ${unitOfMeasureFrom.pluralName} " +
                    "to ${unitOfMeasureTo.pluralName} is impossible\n"
            throw IllegalArgumentException(message)
        }
    }

    private fun convertAndPrintOutput(numberDouble: Double, unitOfMeasureFrom: UnitBase, unitOfMeasureTo: UnitBase) {
        val from = unitOfMeasureFrom.name
        val to = unitOfMeasureTo.name

        val outputNum =
            if (unitOfMeasureFrom.type in listOf('W','L'))
                (numberDouble * unitOfMeasureFrom.multiplayer / unitOfMeasureTo.multiplayer)
            else calculateDegrees(from, to, numberDouble)

        val fromTxt = if (numberDouble != 1.0) unitOfMeasureFrom.pluralName else unitOfMeasureFrom.singularName
        val toTxt = if (outputNum != 1.0) unitOfMeasureTo.pluralName else unitOfMeasureTo.singularName
        println("$numberDouble $fromTxt is $outputNum $toTxt\n")
    }

    private fun calculateDegrees(
        from: String,
        to: String,
        numberDouble: Double
    ): Double {
        return when {
            from == "Fahrenheit" && to == "Celsius" -> (numberDouble - 32) * 5 / 9
            from == "Celsius" && to == "Fahrenheit" -> numberDouble * 9 / 5 + 32
            from == "Celsius" && to == "Kelvin" -> numberDouble + 273.15
            from == "Kelvin" && to == "Celsius" -> numberDouble - 273.15
            from == "Kelvin" && to == "Fahrenheit" -> numberDouble * 9 / 5 - 459.67
            from == "Fahrenheit" && to == "Kelvin" -> (numberDouble + 459.67) * 5 / 9
            from == to -> numberDouble
            else -> 0.0
        }
    }

    private fun getUnitOfMeasure(umString: String): UnitBase {
        for (unit in UnitBase.entries) {
            if (umString.lowercase().matches(unit.pattern)) {
                return unit
            }
        }
        return UnitBase.BLANK
    }
}
