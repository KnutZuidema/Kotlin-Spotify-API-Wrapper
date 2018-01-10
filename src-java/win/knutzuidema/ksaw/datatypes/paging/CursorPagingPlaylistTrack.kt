package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.PlaylistTrack
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class CursorPagingPlaylistTrack(json: JSONObject) : CursorPaging(json), Serializable, Serializer {

    val items: List<PlaylistTrack> = json.getJSONArray("items").map { PlaylistTrack(it as JSONObject) }

    companion object {
        private val serialVerionUID: Long = 0xA02
    }
}
