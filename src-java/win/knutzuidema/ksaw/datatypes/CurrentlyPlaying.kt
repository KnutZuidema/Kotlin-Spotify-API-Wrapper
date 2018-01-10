package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class CurrentlyPlaying(json: JSONObject) : Serializable, Serializer {

    val context: Context = Context(json.getJSONObject("context"))
    val timestamp: Int = json.getInt("timestamp")
    val progress: Int = json.getInt("progress_ms")
    val isPlaying: Boolean = json.getBoolean("is_playing")
    val track: Track = Track(json.getJSONObject("item"))

    companion object {
        private const val serialVersionUID: Long = 0x900
    }
}
