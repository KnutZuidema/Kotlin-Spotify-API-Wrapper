package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class TrackLink(json: JSONObject) : Serializable, Serializer {

    @Transient
    val externalURLs: JSONObject = json.getJSONObject("external_urls")
    val href: String = json.getString("href")
    val id: String = json.getString("id")
    val uri: String = json.getString("uri")

    companion object {
        private const val serialVersionUID: Long = 0x300
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrackLink

        if (externalURLs != other.externalURLs) return false
        if (href != other.href) return false
        if (id != other.id) return false
        if (uri != other.uri) return false

        return true
    }

    override fun hashCode(): Int {
        var result = externalURLs.hashCode()
        result = 31 * result + href.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + uri.hashCode()
        return result
    }
}
