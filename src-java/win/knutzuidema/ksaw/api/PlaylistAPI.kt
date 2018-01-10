package win.knutzuidema.ksaw.api

import org.json.JSONArray
import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Playlist
import win.knutzuidema.ksaw.datatypes.paging.PagingPlaylist
import win.knutzuidema.ksaw.datatypes.paging.PagingPlaylistTrack

object PlaylistAPI {

    fun getPlaylist(userID: String, playlistID: String): Playlist {
        val request = API
                .requestBuilder("GET", "/users/$userID/playlists/$playlistID")
                .build()

        return Playlist(API.getJSON(request)!!)
    }

    fun getPlaylistTracks(userID: String, playlistID: String): PagingPlaylistTrack {
        val request = API
                .requestBuilder("GET", "/users/$userID/playlists/$playlistID/tracks")
                .build()

        return PagingPlaylistTrack(API.getJSON(request)!!)
    }

    fun createPlaylist(userID: String, name: String): Playlist {
        val request = API
                .requestBuilder("POST", "/users/$userID/playlists")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("name", name)
                        .build())
                .build()

        return Playlist(API.getJSON(request)!!)
    }

    fun createPlaylist(userID: String, name: String, isPublic: Boolean, isCollaborative: Boolean, description: String): Playlist {
        val request = API
                .requestBuilder("POST", "/users/$userID/playlists")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("name", name)
                        .addParameter("public", isPublic)
                        .addParameter("collaborative", isCollaborative)
                        .addParameter("description", description)
                        .build())
                .build()

        return Playlist(API.getJSON(request)!!)
    }

    fun getUserPlaylists(userID: String): PagingPlaylist {
        val request = API
                .requestBuilder("GET", "/usesrs/$userID/playlists")
                .build()

        return PagingPlaylist(API.getJSON(request)!!)
    }

    fun setName(userID: String, playlistID: String, name: String) {
        val request = API
                .requestBuilder("PUT", "/users/$userID/playlists/$playlistID")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("name", name)
                        .build())
                .build()

        API.getResponse(request)
    }

    fun setPublic(userID: String, playlistID: String, isPublic: Boolean) {
        val request = API
                .requestBuilder("PUT", "/users/$userID/playlists/$playlistID")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("public", isPublic)
                        .build())
                .build()

        API.getResponse(request)
    }

    fun setCollaborative(userID: String, playlistID: String, isCollaborative: Boolean) {
        val request = API
                .requestBuilder("PUT", "/users/$userID/playlists/$playlistID")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("collaborative", isCollaborative)
                        .build())
                .build()

        API.getResponse(request)
    }

    fun setDescription(userID: String, playlistID: String, description: String) {
        val request = API
                .requestBuilder("PUT", "/users/$userID/playlists/$playlistID")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("description", description)
                        .build())
                .build()

        API.getResponse(request)
    }

    fun addTracks(userID: String, playlistID: String, vararg uris: String) {
        if (uris.size > 100) {
            throw IllegalArgumentException("max of 100 tracks")
        }

        val request = API
                .requestBuilder("POST", "/users/$userID/playlists/$playlistID/tracks")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("uris", uris)
                        .build())
                .build()

        API.getResponse(request)
    }

    fun removeTracks(userID: String, playlistID: String, vararg uris: String) {
        if (uris.size > 100) {
            throw IllegalArgumentException("max of 100 tracks")
        }

        val array = JSONArray()
        for (uri in uris) {
            array.put(JSONObject().put("uri", uri))
        }

        val request = API
                .requestBuilder("DELETE", "/users/$userID/playlists/$playlistID/tracks")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("tracks", uris)
                        .build())
                .build()

        API.getResponse(request)
    }

    fun isFollowing(userID: String, playlistID: String, followerID: String): Boolean {
        val request = API
                .requestBuilder("GET", "/users/$userID/playlists/$playlistID/followers/contains")
                .addParameter("ids", followerID)
                .build()

        return API.getArrayFromResponse(API.getResponse(request)!!)!!.getBoolean(0)
    }

    //TODO reorder, replace, upload cover image
}
