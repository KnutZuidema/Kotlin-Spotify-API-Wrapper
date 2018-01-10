package win.knutzuidema.ksaw.api

import org.json.JSONObject
import win.knutzuidema.ksaw.datatypes.Track

import java.util.LinkedList

object TrackAPI {

    fun getTrack(id: String): Track {
        val request = API
                .requestBuilder("GET", "/tracks/" + id)
                .build()

        return Track(API.getJSON(request)!!)
    }

    fun getTracks(vararg ids: String): List<Track> {
        if (ids.size > 50) {
            throw IllegalArgumentException("max of 50 tracks per request")
        }

        val value = StringBuilder()
        for (i in ids.indices) {
            value.append(ids[i])
            if (i < ids.size - 1) {
                value.append(',')
            }
        }

        val request = API
                .requestBuilder("GET", "/tracks")
                .addParameter("ids", value.toString())
                .build()

        val array = API.getJSON(request)!!.getJSONArray("tracks")

        return array.mapTo(LinkedList()) { Track(it as JSONObject) }
    }
}
