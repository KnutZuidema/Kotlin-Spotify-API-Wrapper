package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class User(val json: JSONObject) : Serializable, Serializer {

    val birthday: String? = if (json.isNull("birthday")) null else json.getString("birthday")
    val country: String? = if (json.isNull("country")) null else json.getString("country")
    val displayName: String? = if (json.isNull("display_name")) null else json.getString("display_name")
    val email: String? = if (json.isNull("email")) null else json.getString("email")
    val externalURLs: JSONObject = json.getJSONObject("external_urls")
    @Transient
    val followers: JSONObject? = if (json.isNull("followers")) null else json.getJSONObject("followers")
    val href: String = json.getString("href")
    val id: String = json.getString("id")
    val images: List<Image>? = if (json.isNull("images")) null else json.getJSONArray("images").map { Image(it as JSONObject) }
    val product: String? = if (json.isNull("product")) null else json.getString("product")
    val type = "user"
    val uri: String = json.getString("uri")

    companion object {
        private const val serialVersionUID: Long = 0x105
    }
}
