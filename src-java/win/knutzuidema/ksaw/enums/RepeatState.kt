package win.knutzuidema.ksaw.enums

import java.io.Serializable

enum class RepeatState : Serializable {
    OFF,
    TRACK,
    CONTEXT;

    override fun toString(): String {
        return this.name.toLowerCase()
    }
}
