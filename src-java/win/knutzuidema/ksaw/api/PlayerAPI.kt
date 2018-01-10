package win.knutzuidema.ksaw.api

import org.json.JSONArray
import win.knutzuidema.ksaw.datatypes.*
import win.knutzuidema.ksaw.datatypes.paging.CursorPagingPlayHistory
import win.knutzuidema.ksaw.enums.RepeatState

import java.util.LinkedList

object PlayerAPI {

    val recentlyPlayed: CursorPagingPlayHistory
        get() {
            val request = API
                    .requestBuilder("GET", "/player/recently-played")
                    .build()

            return CursorPagingPlayHistory(API.getJSON(request)!!)
        }

    val availableDevices: List<Device>
        get() {
            val request = API
                    .requestBuilder("GET", "/me/player/devices")
                    .build()

            val array = API.getJSON(request)!!
                    .getJSONArray("devices")

            return array.mapTo(LinkedList()) { it as Device }
        }

    val player: Player
        get() {
            val request = API
                    .requestBuilder("GET", "/me/player")
                    .build()

            return Player(API.getJSON(request)!!)
        }

    val currentlyPlaying: CurrentlyPlaying?
        get() {
            val request = API
                    .requestBuilder("GET", "/me/player/currently-playing")
                    .build()

            val response = API.getResponse(request)
            return if (response!!.statusLine.statusCode == 204) {
                null
            } else CurrentlyPlaying(API.getJSONfromResponse(response)!!)
        }

    fun play() {
        val request = API
                .requestBuilder("PUT", "/me/player/play")
                .build()

        API.getResponse(request)
    }

    fun play(obj: Playable) {
        val request = API
                .requestBuilder("PUT", "/me/player/play")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter(if (obj is Track) "uris" else "context_uri",
                                if (obj is Track) JSONArray().put(obj.uri) else obj.uri)
                        .build())
                .build()

        API.getResponse(request)
    }

    fun pause() {
        val request = API
                .requestBuilder("PUT", "/me/player/pause")
                .build()

        API.getResponse(request)
    }

    operator fun next() {
        val request = API
                .requestBuilder("POST", "/me/player/next")
                .build()

        API.getResponse(request)
    }

    fun previous() {
        val request = API
                .requestBuilder("POST", "/me/player/previous")
                .build()

        API.getResponse(request)
    }

    fun seek(ms: Int) {
        val request = API
                .requestBuilder("PUT", "/me/player/seek?position_ms=" + ms)
                .build()

        API.getResponse(request)
    }

    fun setRepeat(state: RepeatState) {
        val request = API
                .requestBuilder("PUT", "/me/player/repeat?state=" + state.toString())
                .build()

        API.getResponse(request)
    }

    fun setVolume(percent: Int) {
        val request = API
                .requestBuilder("PUT", "/me/player/volume?volume_percent=" + percent)
                .build()

        API.getResponse(request)
    }

    fun setShuffle(state: Boolean) {
        val request = API
                .requestBuilder("PUT", "/me/player/shuffle?state=" + state)
                .build()

        API.getResponse(request)
    }
}
