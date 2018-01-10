package win.knutzuidema.ksaw.enums

import java.io.Serializable

enum class AlbumType : Serializable {
    ALBUM,
    SINGLE,
    APPEARS_ON,
    COMPILATION;

    override fun toString(): String {
        return this.name.toLowerCase()
    }
}
