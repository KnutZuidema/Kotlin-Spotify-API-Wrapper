package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class Category(json: JSONObject) : Serializable, Serializer {

    val href: String = json.getString("href")
    val icons: List<Image> = json.getJSONArray("icons").map { Image(it as JSONObject) }
    val id: String = json.getString("id")
    val name: String = json.getString("name")

    companion object {
        private const val serialVersionUID: Long = 0x700
    }
}
