package converter

fun main() {
    println("Enter a number and a measure:")
    try {
        val input = readln().split(" ")
        val num = input[0].toInt()
        val unit: String = input[1].lowercase().trim()
        val result = convertInput(num, unit)
        println(result)
    } catch (ex: NumberFormatException) {
        println("Wrong input")
    }
}

fun convertInput (num: Int, unitNormalized: String): String {
    if (unitNormalized == "km" || unitNormalized.matches(Regex("kilometers?"))){
        val converted = num * 1000
        val km = if(num == 1) "kilometer" else "kilometers"
        return "$num $km is $converted meters"
    } else {
        return "Wrong input"
    }
}
