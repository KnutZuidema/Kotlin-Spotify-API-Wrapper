package win.knutzuidema.ksaw.api

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Album

import java.util.LinkedList

object AlbumAPI {

    fun getAlbum(id: String): Album {
        val request = API
                .requestBuilder("GET", "/albums/" + id)
                .build()

        return Album(API.getJSON(request)!!)
    }

    fun getAlbums(vararg ids: String): List<Album> {
        if (ids.size > 20) {
            throw IllegalArgumentException("max of 20 albums")
        }

        val value = StringBuilder()
        for (i in ids.indices) {
            value.append(ids[i])
            if (i < ids.size - 1) {
                value.append(',')
            }
        }

        val request = API.requestBuilder("GET", "/albums")
                .addParameter("ids", value.toString())
                .build()

        val array = API.getJSON(request)!!.getJSONArray("albums")

        return array.mapTo(LinkedList()) { Album(it as JSONObject) }
    }
}
