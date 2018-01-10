package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.enums.ContextType
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class Context(json: JSONObject) : Serializable, Serializer {

    val type: ContextType = ContextType.valueOf(json.getString("type").toUpperCase())
    val uri: String = json.getString("uri")
    val href: String = json.getString("href")
    @Transient
    val externalURLs: JSONObject = json.getJSONObject("external_urls")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val context = other as Context?

        if (type != context!!.type) return false
        if (uri != context.uri) return false
        if (href != context.href) return false
        return externalURLs == context.externalURLs
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + uri.hashCode()
        result = 31 * result + href.hashCode()
        result = 31 * result + externalURLs.hashCode()
        return result
    }

    companion object {
        private const val serialVersionUID: Long = 0x600
    }
}
