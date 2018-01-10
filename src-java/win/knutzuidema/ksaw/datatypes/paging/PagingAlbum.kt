package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Album
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class PagingAlbum(json: JSONObject) : Paging(json), Serializable, Serializer {

    val items: List<Album> = json.getJSONArray("items").map { Album(it as JSONObject) }

    companion object {
        private const val serialVersionUID: Long = 0x201
    }
}
