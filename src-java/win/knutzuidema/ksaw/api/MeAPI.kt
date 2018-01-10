package win.knutzuidema.ksaw.api

import org.json.JSONArray
import win.knutzuidema.ksaw.datatypes.User
import win.knutzuidema.ksaw.datatypes.paging.*
import win.knutzuidema.ksaw.enums.TimeFrame
import java.io.InputStreamReader
import java.util.*

object MeAPI {

    val me: User
        get() {
            val request = API
                    .requestBuilder("GET", "/me")
                    .build()
            InputStreamReader(API.getResponse(request)?.entity?.content).use { isr ->
                while(isr.ready()){
                    print(isr.read().toChar())
                }
            }
            println()

            return User(API.getJSON(request)!!)
        }

    val followedAtists: CursorPagingArtist
        get() {
            val request = API
                    .requestBuilder("GET", "/me/following?type=artist")
                    .build()

            return CursorPagingArtist(API.getJSON(request)!!)
        }

    val savedTracks: PagingTrack
        get() {
            val request = API
                    .requestBuilder("GET", "/me/tracks")
                    .build()

            return PagingTrack(API.getJSON(request)!!)
        }

    val savedAlbums: PagingSavedAlbum
        get() {
            val request = API
                    .requestBuilder("GET", "/me/albums")
                    .build()

            return PagingSavedAlbum(API.getJSON(request)!!)
        }

    val topArtists: PagingArtist
        get() {
            val request = API
                    .requestBuilder("GET", "/me/top?type=artists")
                    .build()

            return PagingArtist(API.getJSON(request)!!)
        }

    val topTracks: PagingTrack
        get() {
            val request = API
                    .requestBuilder("GET", "/me/top?type=tracks")
                    .build()

            return PagingTrack(API.getJSON(request)!!)
        }

    val playlists: PagingPlaylist
        get() {
            val request = API
                    .requestBuilder("GET", "/me/playlists")
                    .build()

            return PagingPlaylist(API.getJSON(request)!!)
        }

    @JvmStatic
    fun followArtists(vararg artistIDs: String) {
        if (artistIDs.size > 50) {
            return
        }

        val request = API
                .requestBuilder("PUT", "/me/following?type=artist")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", JSONArray(artistIDs))
                        .build())
                .build()

        API.getResponse(request)
    }

    @JvmStatic
    fun followUsers(vararg userIDs: String) {
        if (userIDs.size > 50) {
            return
        }

        val request = API
                .requestBuilder("PUT", "/me/following?type=user")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", JSONArray(userIDs))
                        .build())
                .build()

        API.getResponse(request)
    }

    @JvmStatic
    fun unfollowArtists(vararg artistIDs: String) {
        if (artistIDs.size > 50) {
            return
        }

        val request = API
                .requestBuilder("DELETE", "/me/following?type=artist")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", JSONArray(artistIDs))
                        .build())
                .build()

        API.getResponse(request)
    }

    @JvmStatic
    fun unfollowUsers(vararg userIDs: String) {
        if (userIDs.size > 50) {
            return
        }

        val request = API
                .requestBuilder("DELETE", "/me/following?type=user")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", JSONArray(userIDs))
                        .build())
                .build()

        API.getResponse(request)
    }

    fun isFollowingArtist(artistID: String): Boolean {
        val request = API
                .requestBuilder("GET", "/me/following/contains?type=artist")
                .addParameter("ids", artistID)
                .build()

        return API.getArrayFromResponse(API.getResponse(request)!!)!!.getBoolean(0)
    }

    fun isFollowingUser(userID: String): Boolean {
        val request = API
                .requestBuilder("PUT", "/me/following/contains?type=user")
                .addParameter("ids", userID)
                .build()

        return API.getArrayFromResponse(API.getResponse(request)!!)!!.getBoolean(0)
    }

    @JvmStatic
    fun saveTracks(vararg ids: String) {
        if (ids.size > 50) {
            throw IllegalArgumentException("max of 50 tracks")
        } else if (ids.isEmpty()) {
            return
        }

        val request = API
                .requestBuilder("PUT", "/me/tracks")
                .addHeader("Content-Type", "application/json")
                .addParameter("ids", API.toQueryString(Arrays.asList(*ids)))
                .build()

        API.getResponse(request)
    }

    @JvmStatic
    fun removeSavedTracks(vararg ids: String) {
        if (ids.size > 50) {
            throw IllegalArgumentException("max of 50 tracks")
        } else if (ids.isEmpty()) {
            return
        }

        val request = API
                .requestBuilder("DELETE", "/me/tracks")
                .addParameter("ids", API.toQueryString(Arrays.asList(*ids)))
                .build()

        API.getResponse(request)
    }

    fun savedTracksContain(id: String): Boolean {
        val request = API
                .requestBuilder("GET", "/me/tracks/contains")
                .addParameter("ids", id)
                .build()

        return API
                .getArrayFromResponse(API.getResponse(request)!!)!!
                .getBoolean(0)
    }

    @JvmStatic
    fun saveAlbums(vararg ids: String) {
        if (ids.size > 50) {
            throw IllegalArgumentException("max of 50 albums")
        } else if (ids.isEmpty()) {
            return
        }

        val request = API
                .requestBuilder("PUT", "/me/albums")
                .addHeader("Content-Type", "application/json")
                .addParameter("ids", API.toQueryString(Arrays.asList(*ids)))
                .build()

        API.getResponse(request)
    }

    @JvmStatic
    fun deleteSavedAlbums(vararg ids: String) {
        if (ids.size > 50) {
            throw IllegalArgumentException("max of 50 albums")
        } else if (ids.isEmpty()) {
            return
        }

        val request = API
                .requestBuilder("DELETE", "/me/albums")
                .addParameter("ids", API.toQueryString(Arrays.asList(*ids)))
                .build()

        API.getResponse(request)
    }

    fun savedAlbumsContain(id: String): Boolean {
        val request = API
                .requestBuilder("GET", "/me/albums/contains")
                .addParameter("ids", id)
                .build()

        return API
                .getArrayFromResponse(API.getResponse(request)!!)!!
                .getBoolean(0)
    }

    fun getTopArtists(timeframe: TimeFrame): PagingArtist {
        val request = API
                .requestBuilder("GET", "/me/top/artists")
                .addParameter("time_range", timeframe.toString())
                .build()

        return PagingArtist(API.getJSON(request)!!)
    }

    fun getTopTracks(timeframe: TimeFrame): PagingTrack {
        val request = API
                .requestBuilder("GET", "/me/top/tracks")
                .addParameter("time_range", timeframe.toString())
                .build()

        return PagingTrack(API.getJSON(request)!!)
    }
}
