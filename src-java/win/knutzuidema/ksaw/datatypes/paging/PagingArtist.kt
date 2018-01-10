package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Artist
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class PagingArtist(json: JSONObject) : Paging(json), Serializable, Serializer {

    val items: List<Artist> = json.getJSONArray("items").map { Artist(it as JSONObject) }

    companion object {
        private const val serialVersionUID: Long = 0x202
    }
}
