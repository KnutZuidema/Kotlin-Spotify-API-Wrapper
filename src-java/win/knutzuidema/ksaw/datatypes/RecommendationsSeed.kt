package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class RecommendationsSeed(json: JSONObject) : Serializable, Serializer {

    val id: String = json.getString("id")
    val type: String = json.getString("type")
    val initialPoolSize: Int = json.getInt("initialPoolSize")
    val afterFilteringSize: Int = json.getInt("afterFilteringSize")
    val afterRelinkingSize: Int = json.getInt("afterRelinkingSize")
    val href: String? = if (json.isNull("href")) null else json.getString("href")

    companion object {
        private const val serialVersionUID: Long = 0xD00
    }
}
