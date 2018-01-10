package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Track
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class PagingTrack(json: JSONObject) : Paging(json), Serializable, Serializer {

    var items: List<Track> = json.getJSONArray("items").map { Track(it as JSONObject) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        if (!super.equals(other)) return false

        val that = other as PagingTrack

        return items == that.items
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + items.hashCode()
        return result
    }

    companion object {
        private const val serialVersionUID: Long = 0x203
    }
}
