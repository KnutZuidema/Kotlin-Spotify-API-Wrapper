package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class PlaylistTrack(json: JSONObject) : Serializable, Serializer {

    val addedAt: String? = if (json.isNull("added_at")) null else json.getString("added_at")
    val addedBy: User? = if (json.isNull("added_by")) null else User(json.getJSONObject("added_by"))
    val isLocal: Boolean = json.getBoolean("is_local")
    val track: Track = Track(json.getJSONObject("track"))

    companion object {
        private const val serialVersionUID: Long = 0x800
    }
}
