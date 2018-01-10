package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class Track(json: JSONObject) : Playable(json), Serializable, Serializer {

    var album: Album? = if (json.isNull("album")) null else Album(json.getJSONObject("album"))
    val artists: List<Artist> = json.getJSONArray("artists").map { Artist(it as JSONObject) }
    val availableMarkets: List<String>? = if (json.isNull("available_markets")) null else json.getJSONArray("available_markets").map { it as String }
    val discNumber: Int = json.getInt("disc_number")
    val durationMs: Int = json.getInt("duration_ms")
    val isExplicit: Boolean = json.getBoolean("explicit")
    var externalIDs: JSONObject? = if (json.isNull("external_ids")) null else json.getJSONObject("external_ids")
    var isPlayable: Boolean? = if (json.isNull("is_playable")) null else json.getBoolean("is_playable")
    var linkedFrom: TrackLink? = if (json.isNull("linked_from")) null else TrackLink(json.getJSONObject("linked_from"))
    var restrictions: JSONObject? = if (json.isNull("restrictions")) null else json.getJSONObject("restrictions")
    var popularity: Int? = if (json.isNull("popularity")) null else json.getInt("popularity")
    var previewURL: String? = if (json.isNull("preview_url")) null else json.getString("preview_url")
    val trackNumber: Int = json.getInt("track_number")

    fun complete(): Track{
        val json = jsonFromHref()
        this.album = Album(json.getJSONObject("album"))
        this.externalIDs = json.getJSONObject("external_ids")
        this.isPlayable = if(json.isNull("is_playable")) null else json.getBoolean("is_playable")
        this.linkedFrom = if(json.isNull("linked_from")) null else TrackLink(json.getJSONObject("linked_from"))
        this.restrictions = if(json.isNull("restrictions")) null else json.getJSONObject("restrictions")
        this.popularity = json.getInt("popularity")
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as Track

        if (album != other.album) return false
        if (artists != other.artists) return false
        if (availableMarkets != other.availableMarkets) return false
        if (discNumber != other.discNumber) return false
        if (durationMs != other.durationMs) return false
        if (isExplicit != other.isExplicit) return false
        if (externalIDs != other.externalIDs) return false
        if (isPlayable != other.isPlayable) return false
        if (linkedFrom != other.linkedFrom) return false
        if (restrictions != other.restrictions) return false
        if (popularity != other.popularity) return false
        if (previewURL != other.previewURL) return false
        if (trackNumber != other.trackNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (album?.hashCode() ?: 0)
        result = 31 * result + artists.hashCode()
        result = 31 * result + (availableMarkets?.hashCode() ?: 0)
        result = 31 * result + discNumber
        result = 31 * result + durationMs
        result = 31 * result + isExplicit.hashCode()
        result = 31 * result + (externalIDs?.hashCode() ?: 0)
        result = 31 * result + (isPlayable?.hashCode() ?: 0)
        result = 31 * result + (linkedFrom?.hashCode() ?: 0)
        result = 31 * result + (restrictions?.hashCode() ?: 0)
        result = 31 * result + (popularity ?: 0)
        result = 31 * result + (previewURL?.hashCode() ?: 0)
        result = 31 * result + trackNumber
        result = 31 * result + type.hashCode()
        return result
    }

    companion object {
        private const val serialVersionUID: Long = 0x104
    }

}
