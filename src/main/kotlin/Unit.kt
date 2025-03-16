package converter

enum class Unit(
    val singularName: String,
    val pluralName: String = singularName,
    val pattern: Regex,
    val amountInMeter: Double,
) {
    METERS("meter", "meters", pattern = Regex("""m|meters?"""), amountInMeter = 1.0),
    KM("kilometer", "kilometers", pattern = Regex("km|kilometers?"), amountInMeter = 1000.0),
    CM("centimeter", "centimeters", pattern = Regex("cm|centimeters?"), amountInMeter = 0.01),
    MM("millimeter", "millimeters", pattern = Regex("mm|millimeters?"), amountInMeter = 0.001),
    MI("mile", "miles", pattern = Regex("mi|miles?"), amountInMeter = 1609.35),
    YD("yard", "yards", pattern = Regex("yd|yards?"), amountInMeter = 0.9144),
    FT("foot", "feet", Regex("ft|foot|feet"), amountInMeter = 0.3048),
    IN("inch", "inches", Regex("in|inch(es)?"), amountInMeter = 0.0254)
}