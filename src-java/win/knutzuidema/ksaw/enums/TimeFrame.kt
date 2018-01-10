package win.knutzuidema.ksaw.enums

enum class TimeFrame {
    LONG_TERM,
    MEDIUM_TERM,
    SHORT_TERM;

    override fun toString(): String {
        return this.name.toLowerCase()
    }
}
