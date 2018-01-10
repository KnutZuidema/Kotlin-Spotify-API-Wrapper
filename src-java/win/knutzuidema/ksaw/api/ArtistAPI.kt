package win.knutzuidema.ksaw.api

import com.sun.istack.internal.Nullable
import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Artist
import win.knutzuidema.ksaw.datatypes.paging.PagingAlbum
import win.knutzuidema.ksaw.datatypes.Track
import win.knutzuidema.ksaw.enums.AlbumType

import java.util.LinkedList

object ArtistAPI {

    fun getArtist(id: String): Artist {

        val request = API
                .requestBuilder("GET", "/artists/" + id)
                .build()

        return Artist(API.getJSON(request)!!)
    }

    fun getArtists(vararg ids: String): List<Artist> {
        if (ids.size > 50) {
            throw IllegalArgumentException("max of 50 artists")
        }

        val value = StringBuilder()
        for (i in ids.indices) {
            value.append(ids[i])
            if (i < ids.size - 1) {
                value.append(',')
            }
        }

        val request = API
                .requestBuilder("GET", "/artists")
                .addParameter("ids", value.toString())
                .build()

        val array = API.getJSON(request)!!
                .getJSONArray("artists")

        return array.mapTo(LinkedList()) { Artist(it as JSONObject) }
    }

    fun getRelatedArtists(id: String): List<Artist> {
        val request = API
                .requestBuilder("GET", "/artists/$id/related-artists")
                .build()

        val array = API.getJSON(request)!!
                .getJSONArray("artists")

        return array.mapTo(LinkedList()) { Artist(it as JSONObject) }
    }

    fun getAlbums(id: String, @Nullable vararg types: AlbumType): PagingAlbum {
        val value = StringBuilder()
        for (i in types.indices) {
            value.append(types[i].toString())
            if (i < types.size - 1) {
                value.append(',')
            }
        }

        val request = API
                .requestBuilder("GET", "/artists/$id/albums")
                .addParameter("album_type", value.toString())
                .build()

        return PagingAlbum(API.getJSON(request)!!)
    }

    fun getTopTracks(id: String): List<Track> {

        val request = API
                .requestBuilder("GET", "/artists/" + id + "top-tracks")
                .build()

        val array = API.getJSON(request)!!
                .getJSONArray("tracks")

        return array.mapTo(LinkedList()) { Track(it as JSONObject) }
    }
}
