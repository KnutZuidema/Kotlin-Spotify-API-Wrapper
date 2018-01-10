package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Category
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class CursorPagingCategory(json: JSONObject) : CursorPaging(json), Serializable, Serializer {

    val items: List<Category> = json.getJSONArray("items").map { Category(it as JSONObject) }

    companion object {
        private val serialVerionUID: Long = 0xA04
    }
}
