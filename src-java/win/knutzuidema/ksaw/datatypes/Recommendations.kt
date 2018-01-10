package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class Recommendations(json: JSONObject) : Serializable, Serializer {

    val tracks: List<Track> = json.getJSONArray("tracks").map { Track(it as JSONObject) }
    val seeds: List<RecommendationsSeed> = json.getJSONArray("seeds").map { RecommendationsSeed(it as JSONObject) }

    companion object {
        private const val serialVersionUID: Long = 0xE00
    }
}
