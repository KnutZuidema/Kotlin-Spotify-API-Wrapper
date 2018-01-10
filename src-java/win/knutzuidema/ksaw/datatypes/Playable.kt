package win.knutzuidema.ksaw.datatypes

import org.apache.http.client.methods.RequestBuilder
import org.json.JSONObject
import win.knutzuidema.ksaw.api.API
import win.knutzuidema.ksaw.api.Authentication

import java.io.Serializable

abstract class Playable protected constructor(json: JSONObject) : Serializable {

    val type: String = json.getString("type")
    val id: String = json.getString("id")
    val uri: String = json.getString("uri")
    val name: String = json.getString("name")
    val href: String = json.getString("href")
    @Transient
    var externalURLs: JSONObject = json.getJSONObject("external_urls")

    protected fun jsonFromHref(): JSONObject{
        return API.getJSON(RequestBuilder.create("GET").setUri(href).addHeader(Authentication.bearerAuth()).build())!!
    }

    override fun toString(): String {
        return this.name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Playable

        if (id != other.id) return false
        if (uri != other.uri) return false
        if (name != other.name) return false
        if (href != other.href) return false
        if (externalURLs != other.externalURLs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + uri.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + href.hashCode()
        result = 31 * result + externalURLs.hashCode()
        return result
    }

    companion object {
        private const val serialVersionUID: Long = 0x100
    }
}
