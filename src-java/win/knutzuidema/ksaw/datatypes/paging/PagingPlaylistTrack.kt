package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.PlaylistTrack
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class PagingPlaylistTrack(json: JSONObject) : Paging(json), Serializable, Serializer {
    var items: List<PlaylistTrack> = json.getJSONArray("items").map { PlaylistTrack(it as JSONObject) }

    companion object {
        private const val serialVersionUID: Long = 0x207
    }
}
