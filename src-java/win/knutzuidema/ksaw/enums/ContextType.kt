package win.knutzuidema.ksaw.enums

import java.io.Serializable

enum class ContextType : Serializable {
    ALBUM,
    ARTIST,
    PLAYLIST;

    override fun toString(): String {
        return this.name.toLowerCase()
    }
}
