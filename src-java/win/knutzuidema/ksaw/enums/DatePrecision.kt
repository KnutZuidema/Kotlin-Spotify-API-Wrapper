package win.knutzuidema.ksaw.enums

import java.io.Serializable

enum class DatePrecision : Serializable {
    YEAR,
    MONTH,
    DAY;

    override fun toString(): String {
        return this.name.toLowerCase()
    }
}
