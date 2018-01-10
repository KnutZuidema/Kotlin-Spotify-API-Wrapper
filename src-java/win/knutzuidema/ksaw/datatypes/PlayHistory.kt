package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class PlayHistory(json: JSONObject) : Serializable, Serializer {

    val track: Track = Track(json.getJSONObject("track"))
    val playedAt: String = json.getString("timestamp")
    val context: Context = Context(json.getJSONObject("context"))

    companion object {
        private const val serialVersionUID: Long = 0xC00
    }
}
