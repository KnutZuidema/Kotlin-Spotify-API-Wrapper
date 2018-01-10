package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Playlist
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class PagingPlaylist(json: JSONObject) : Paging(json), Serializable, Serializer {

    val items: List<Playlist> = json.getJSONArray("items").map { Playlist(it as JSONObject) }

    companion object {
        private const val serialVersionUID: Long = 0x204
    }
}
