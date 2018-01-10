package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class Image(json: JSONObject) : Serializable, Serializer {

    val url: String = json.getString("url")
    val width: Int? = if (json.isNull("width")) null else json.getInt("width")
    val height: Int? = if (json.isNull("height")) null else json.getInt("height")

    companion object {
        private const val serialVersionUID: Long = 0x00
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        return url.hashCode()
    }
}
