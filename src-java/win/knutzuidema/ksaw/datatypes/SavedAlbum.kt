package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class SavedAlbum(json: JSONObject) : Serializable, Serializer {

    val addedAt: String = json.getString("added_at")
    val album: Album = Album(json.getJSONObject("album"))

    companion object {
        private const val serialVersionUID: Long = 0x1000
    }
}
