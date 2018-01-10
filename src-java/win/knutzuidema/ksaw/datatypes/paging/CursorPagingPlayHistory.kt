package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.PlayHistory
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class CursorPagingPlayHistory(json: JSONObject) : CursorPaging(json), Serializable, Serializer {

    val items: List<PlayHistory> = json.getJSONArray("items").map { PlayHistory(it as JSONObject) }

    companion object {
        private const val serialVersionUID: Long = 0xA07
    }
}
