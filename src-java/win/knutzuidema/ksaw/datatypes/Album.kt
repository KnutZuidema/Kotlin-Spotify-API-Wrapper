package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.paging.PagingTrack
import win.knutzuidema.ksaw.enums.AlbumType
import win.knutzuidema.ksaw.enums.DatePrecision
import win.knutzuidema.ksaw.enums.Genre
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class Album(json: JSONObject) : Playable(json), Serializable, Serializer {

    val albumType: AlbumType = AlbumType.valueOf(json.getString("album_type").toUpperCase())
    val artists: List<Artist>? = if (json.isNull("artists")) null else json.getJSONArray("artists").map { Artist(it as JSONObject) }
    val availableMarkets: List<String>? = if (json.isNull("available_markets")) null else json.getJSONArray("available_markets").map { it as String }
    var copyrights: JSONObject? = if (json.isNull("copyrights")) null else json.getJSONObject("copyrights")
    var externalIDs: JSONObject? = if (json.isNull("external_ids")) null else json.getJSONObject("external_ids")
    var genres: List<Genre>? = if (json.isNull("genres")) null else json.getJSONArray("genres").map { Genre.valueOf((it as String).toUpperCase().replace('-', '_')) }
    val images: List<Image> = json.getJSONArray("images").map { Image(it as JSONObject) }
    var label: String? = if (json.isNull("label")) null else json.getString("label")
    var popularity: Int? = if (json.isNull("popularity")) null else json.getInt("popularity")
    var releaseDate: String? = if (json.isNull("release_date")) null else json.getString("release_date")
    var releaseDatePrecision: DatePrecision? = if (json.isNull("release_date_precision")) null else DatePrecision.valueOf(json.getString("release_date_precision").toUpperCase())
    var tracks: PagingTrack? = if (json.isNull("tracks")) null else PagingTrack(json.getJSONObject("tracks"))

    fun complete(): Album{
        val json = jsonFromHref()
        this.copyrights = json.getJSONObject("copyrights")
        this.externalIDs = json.getJSONObject("external_ids")
        this.genres = json.getJSONArray("genres").map { Genre.valueOf((it as String).toUpperCase().replace('-', '_')) }
        this.label = json.getString("label")
        this.popularity = json.getInt("popularity")
        this.releaseDate = json.getString("release_date")
        this.releaseDatePrecision = DatePrecision.valueOf(json.getString("release_date_precision").toUpperCase())
        this.tracks = PagingTrack(json.getJSONObject("tracks"))
        return this
    }

    companion object {
        private const val serialVersionUID: Long = 0x101
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as Album

        if (albumType != other.albumType) return false
        if (artists != other.artists) return false
        if (availableMarkets != other.availableMarkets) return false
        if (copyrights != other.copyrights) return false
        if (externalIDs != other.externalIDs) return false
        if (genres != other.genres) return false
        if (images != other.images) return false
        if (label != other.label) return false
        if (popularity != other.popularity) return false
        if (releaseDate != other.releaseDate) return false
        if (releaseDatePrecision != other.releaseDatePrecision) return false
        if (tracks != other.tracks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + albumType.hashCode()
        result = 31 * result + (artists?.hashCode() ?: 0)
        result = 31 * result + (availableMarkets?.hashCode() ?: 0)
        result = 31 * result + (copyrights?.hashCode() ?: 0)
        result = 31 * result + (externalIDs?.hashCode() ?: 0)
        result = 31 * result + (genres?.hashCode() ?: 0)
        result = 31 * result + images.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + (popularity ?: 0)
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (releaseDatePrecision?.hashCode() ?: 0)
        result = 31 * result + (tracks?.hashCode() ?: 0)
        result = 31 * result + type.hashCode()
        return result
    }
}
