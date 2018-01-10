package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Track
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class CursorPagingTrack(json: JSONObject) : CursorPaging(json), Serializable, Serializer {

    val items: List<Track> = json.getJSONArray("items").map { Track(it as JSONObject) }

    companion object {
        private val serialVerionUID: Long = 0xA01
    }
}
