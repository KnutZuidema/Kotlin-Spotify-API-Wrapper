package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

abstract class CursorPaging(json: JSONObject) : Serializable, Serializer {

    val href: String = json.getString("href")
    val limit: Int = json.getInt("limit")
    val next: String? = if (json.isNull("next")) null else json.getString("next")
    val cursors: Cursor = Cursor(json.getJSONObject("cursors"))
    val total: Int = json.getInt("total")

    companion object {
        private const val serialVersionUID: Long = 0xA00
    }
}
