package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class Cursor(json: JSONObject) : Serializable, Serializer {

    val after: String = json.getString("after")

    companion object {
        private const val serialVersionUID: Long = 0xB00
    }
}
