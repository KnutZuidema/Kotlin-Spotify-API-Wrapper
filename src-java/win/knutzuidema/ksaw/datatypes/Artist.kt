package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.enums.Genre
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class Artist(json: JSONObject) : Playable(json), Serializable, Serializer {

    var followers: JSONObject? = if (json.isNull("followers")) null else json.getJSONObject("followers")
    var genres: List<Genre>? = if (json.isNull("genres")) null else json.getJSONArray("genres").map { Genre.valueOf((it as String).toUpperCase().replace('-', '_')) }
    var images: List<Image>? = if (json.isNull("images")) null else json.getJSONArray("images").map { Image(it as JSONObject) }
    var popularity: Int? = if (json.isNull("popularity")) null else json.getInt("popularity")

    fun complete(): Artist{
        val json = jsonFromHref()
        this.followers = json.getJSONObject("followers")
        this.genres = json.getJSONArray("genres").map { Genre.valueOf((it as String).toUpperCase().replace('-', '_')) }
        this.images = json.getJSONArray("images").map { Image(it as JSONObject) }
        this.popularity = json.getInt("popularity")
        return this
    }
    companion object {
        private const val serialVersionUID: Long = 0x102
    }
}
