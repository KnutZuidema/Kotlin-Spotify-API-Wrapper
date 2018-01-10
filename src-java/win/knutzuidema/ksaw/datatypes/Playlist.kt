package win.knutzuidema.ksaw.datatypes

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.paging.PagingPlaylistTrack
import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

class Playlist(json: JSONObject) : Playable(json), Serializable, Serializer {

    val isCollaborative: Boolean = json.getBoolean("collaborative")
    var description: String? = if (json.isNull("description")) null else json.getString("description")
    var followers: JSONObject? = if (json.isNull("followers")) null else json.getJSONObject("followers")
    val images: List<Image> = json.getJSONArray("images").map { Image(it as JSONObject) }
    val owner: User = User(json.getJSONObject("owner"))
    var isPublic: Boolean? = if (json.isNull("public")) null else json.getBoolean("public")
    val snapshotID: String = json.getString("snapshot_id")
    var tracks: PagingPlaylistTrack? = if (json.isNull("followers")) null else PagingPlaylistTrack(json.getJSONObject("tracks"))
    val type = "playlist"

    fun complete(): Playlist{
        val json = jsonFromHref()
        this.description = json.getString("description")
        this.followers = json.getJSONObject("followers")
        this.isPublic = json.getBoolean("public")
        this.tracks = PagingPlaylistTrack(json.getJSONObject("tracks"))
        return this
    }

    companion object {
        private const val serialVersionUID: Long = 0x103
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as Playlist

        if (isCollaborative != other.isCollaborative) return false
        if (images != other.images) return false
        if (owner != other.owner) return false
        if (isPublic != other.isPublic) return false
        if (snapshotID != other.snapshotID) return false
        if (tracks != other.tracks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + isCollaborative.hashCode()
        result = 31 * result + images.hashCode()
        result = 31 * result + owner.hashCode()
        result = 31 * result + (isPublic?.hashCode() ?: 0)
        result = 31 * result + snapshotID.hashCode()
        result = 31 * result + (tracks?.hashCode() ?: 0)
        return result
    }
}
