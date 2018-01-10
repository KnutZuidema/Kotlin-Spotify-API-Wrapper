package win.knutzuidema.ksaw.datatypes

import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class RecommendationAttributes : Serializable, Serializer {

    var acousticness: Float? = null
        set(value) {
            if (acousticness != null && acousticness!! !in 0.0..1.0) {
                throw IllegalArgumentException("value needs to be between 0.0 and 1.0")
            }
            field = value
        }
    var danceability: Float? = null
        set(value) {
            if (danceability != null && danceability!! !in 0.0..1.0) {
                throw IllegalArgumentException("value needs to be between 0.0 and 1.0")
            }
            field = value
        }
    var durationMs: Int? = null
    var energy: Float? = null
        set(value) {
            if (energy != null && energy!! !in 0.0..1.0) {
                throw IllegalArgumentException("value needs to be between 0.0 and 1.0")
            }
            field = value
        }
    var instrumentalness: Float? = null
        set(value) {
            if (instrumentalness != null && instrumentalness!! !in 0.0..1.0) {
                throw IllegalArgumentException("value needs to be between 0.0 and 1.0")
            }
            field = value
        }
    var key: Int? = null
    var liveness: Float? = null
        set(value) {
            if (liveness != null && liveness!! !in 0.0..1.0) {
                throw IllegalArgumentException("value needs to be between 0.0 and 1.0")
            }
            field = value
        }
    var loudness: Float? = null
    var mode: Int? = null
    var popularity: Int? = null
    var speechiness: Float? = null
        set(value) {
            if (speechiness != null && speechiness!! !in 0.0..1.0) {
                throw IllegalArgumentException("value needs to be between 0.0 and 1.0")
            }
            field = value
        }
    var tempo: Float? = null
    var timeSignature: Int? = null
    var valence: Float? = null
        set(value) {
            if (valence != null && valence!! !in 0.0..1.0) {
                throw IllegalArgumentException("value needs to be between 0.0 and 1.0")
            }
            field = value
        }
    var usedFirst = false

    fun toURIQuery(prefix: String, isFirst: Boolean): String {
        return (if (this.acousticness == null) "" else pre(isFirst) + prefix + "_acousticness=" + this.acousticness) +
                (if (this.danceability == null) "" else pre(isFirst) + prefix + "_danceability=" + this.danceability) +
                (if (this.durationMs == null) "" else pre(isFirst) + prefix + "_duration_ms=" + this.durationMs) +
                (if (this.energy == null) "" else pre(isFirst) + prefix + "_energy=" + this.energy) +
                (if (this.instrumentalness == null) "" else pre(isFirst) + prefix + "_instrumentalness=" + this.instrumentalness) +
                (if (this.key == null) "" else pre(isFirst) + prefix + "_key=" + this.key) +
                (if (this.liveness == null) "" else pre(isFirst) + prefix + "_liveness=" + this.liveness) +
                (if (this.loudness == null) "" else pre(isFirst) + prefix + "_loudness=" + this.loudness) +
                (if (this.mode == null) "" else pre(isFirst) + prefix + "_mode=" + this.mode) +
                (if (this.popularity == null) "" else pre(isFirst) + prefix + "_popularity=" + this.popularity) +
                (if (this.speechiness == null) "" else pre(isFirst) + prefix + "_speechiness=" + this.speechiness) +
                (if (this.tempo == null) "" else pre(isFirst) + prefix + "_tempo=" + this.tempo) +
                (if (this.timeSignature == null) "" else pre(isFirst) + prefix + "_time_signature=" + this.timeSignature) +
                if (this.valence == null) "" else pre(isFirst) + prefix + "_valence=" + this.valence
    }

    private fun pre(arg: Boolean): String {
        if (arg) {
            if (!this.usedFirst) {
                this.usedFirst = true
                return "?"
            }
        }
        return "&"
    }

    companion object {
        private const val serialVersionUID: Long = 0xF00
    }
}
