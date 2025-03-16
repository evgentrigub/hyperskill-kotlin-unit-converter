package converter

enum class UnitBase(
    val type: Char,
    val singularName: String,
    val pluralName: String = singularName,
    val pattern: Regex,
    val multiplayer: Double,
) {
    METERS('L', "meter", "meters", Regex("""m|meters?"""), 1.0),
    KM('L',"kilometer", "kilometers", Regex("km|kilometers?"), 1000.0),
    CM('L',"centimeter", "centimeters", Regex("cm|centimeters?"), 0.01),
    MM('L',"millimeter", "millimeters", Regex("mm|millimeters?"), 0.001),
    MI('L',"mile", "miles", Regex("mi|miles?"), 1609.35),
    YD('L',"yard", "yards", Regex("yd|yards?"), 0.9144),
    FT('L',"foot", "feet", Regex("ft|foot|feet"), 0.3048),
    IN('L',"inch", "inches", Regex("in|inch(es)?"), 0.0254),
    Gram('W', "gram", "grams", Regex("""g|grams?"""),1.0),
    Kilogram('W', "kilogram", "kilograms", Regex("""kg|kilograms?"""),1000.0),
    Milligram('W', "milligram", "milligrams", Regex("""mg|milligrams?"""),0.001),
    Pound('W', "pound", "pounds", Regex("""lb|pounds?"""),453.592),
    Ounce('W', "ounce", "ounces", Regex("""oz|ounce(s)?"""),28.3495),
    BLANK('0', "", "", Regex(""),0.0)
}