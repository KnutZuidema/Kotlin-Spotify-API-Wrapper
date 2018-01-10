package win.knutzuidema.ksaw.api

import win.knutzuidema.ksaw.datatypes.paging.*
import java.util.*

object SearchAPI {

    fun search(query: String): Map<String, Paging> {
        val request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "album,artist,track,playlist")
                .build()

        val json = API.getJSON(request)
        val map = LinkedHashMap<String, Paging>()
        if (!json!!.isNull("albums")) {
            map.put("albums", PagingAlbum(json.getJSONObject("albums")))
        }
        if (!json.isNull("artists")) {
            map.put("artists", PagingArtist(json.getJSONObject("artists")))
        }
        if (!json.isNull("tracks")) {
            map.put("tracks", PagingTrack(json.getJSONObject("tracks")))
        }
        if (!json.isNull("playlists")) {
            map.put("playlists", PagingPlaylist(json.getJSONObject("playlists")))
        }

        return map
    }

    fun searchAlbum(query: String): PagingAlbum {
        val request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "album")
                .build()

        return PagingAlbum(API.getJSON(request)!!.getJSONObject("albums"))
    }

    fun searchArtist(query: String): PagingArtist {
        val request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "artist")
                .build()

        return PagingArtist(API.getJSON(request)!!.getJSONObject("artist"))
    }

    fun searchTrack(query: String): PagingTrack {
        val request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "track")
                .build()

        return PagingTrack(API.getJSON(request)!!.getJSONObject("tracks"))
    }

    fun searchPlaylist(query: String): PagingPlaylist {
        val request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "playlist")
                .build()

        return PagingPlaylist(API.getJSON(request)!!.getJSONObject("playlists"))
    }
}
