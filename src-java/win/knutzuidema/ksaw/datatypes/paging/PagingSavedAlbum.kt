package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.SavedAlbum
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class PagingSavedAlbum(json: JSONObject) : Paging(json), Serializable, Serializer {

    val items: List<SavedAlbum> = json.getJSONArray("items").map { SavedAlbum(it as JSONObject) }

    companion object {
        private const val serialVersionUID: Long = 0x206
    }
}
