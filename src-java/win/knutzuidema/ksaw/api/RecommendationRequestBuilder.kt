package win.knutzuidema.ksaw.api

import win.knutzuidema.ksaw.datatypes.Artist
import win.knutzuidema.ksaw.datatypes.RecommendationAttributes
import win.knutzuidema.ksaw.datatypes.Track
import win.knutzuidema.ksaw.enums.Genre
import java.util.*

class RecommendationRequestBuilder private constructor() {
    private var limit: Int = 0
    private var market: String? = null
    private val max: RecommendationAttributes = RecommendationAttributes()
    private val min: RecommendationAttributes = RecommendationAttributes()
    private val target: RecommendationAttributes = RecommendationAttributes()
    private val trackSeeds: MutableList<Track>
    private val artistSeeds: MutableList<Artist>
    private val genreSeeds: MutableList<Genre>
    private var seeds = 0
    private var isFirst = false

    init {
        this.trackSeeds = LinkedList()
        this.artistSeeds = LinkedList()
        this.genreSeeds = LinkedList()
    }

    fun build(): String {
        val tracks = StringBuilder()
        val artists = StringBuilder()
        val genres = StringBuilder()
        for (i in trackSeeds.indices) {
            tracks.append(trackSeeds[i].id)
            if (i < trackSeeds.size - 1) {
                tracks.append(',')
            }
        }
        for (i in artistSeeds.indices) {
            artists.append(artistSeeds[i].id)
            if (i < artistSeeds.size - 1) {
                artists.append(',')
            }
        }
        for (i in genreSeeds.indices) {
            genres.append(genreSeeds[i].toString())
            if (i < genreSeeds.size - 1) {
                genres.append(',')
            }
        }

        return (if (limit == 0) "" else pre() + "limit=" + limit) +
                (if (market == null) "" else pre() + "market=" + market) +
                (if (trackSeeds.isEmpty()) "" else pre() + "seed_tracks=" + tracks) +
                (if (artistSeeds.isEmpty()) "" else pre() + "seed_artists=" + artists) +
                (if (genreSeeds.isEmpty()) "" else pre() + "seed_genres=" + genres) +
                max.toURIQuery("max", false) +
                min.toURIQuery("min", false) +
                target.toURIQuery("target", false)
    }

    fun isFirst(isFirst: Boolean): RecommendationRequestBuilder {
        this.isFirst = isFirst
        return this
    }

    fun setLimit(limit: Int): RecommendationRequestBuilder {
        assert(limit in 1..100) { "limit out of bounds" }
        this.limit = limit
        return this
    }

    fun setMarket(market: String): RecommendationRequestBuilder {
        this.market = market
        return this
    }

    fun addSeed(seed: Genre): RecommendationRequestBuilder {
        assert(seeds < 6) { "too many seeds" }
        this.genreSeeds.add(seed)
        this.seeds++
        return this
    }

    fun addSeed(seed: Artist): RecommendationRequestBuilder {
        assert(seeds < 6) { "too many seeds" }
        this.artistSeeds.add(seed)
        this.seeds++
        return this
    }

    fun addSeed(seed: Track): RecommendationRequestBuilder {
        assert(seeds < 6) { "too many seeds" }
        this.trackSeeds.add(seed)
        this.seeds++
        return this
    }

    fun setMaxAcousticness(acousticness: Float): RecommendationRequestBuilder {
        this.max.acousticness = acousticness
        return this
    }

    fun setMaxDanceability(danceability: Float): RecommendationRequestBuilder {
        this.max.danceability = danceability
        return this
    }

    fun setMaxDurationMs(duration_ms: Int): RecommendationRequestBuilder {
        this.max.durationMs = duration_ms
        return this
    }

    fun setMaxEnergy(energy: Float): RecommendationRequestBuilder {
        this.max.energy = energy
        return this
    }

    fun setMaxInstrumentalness(instrumentalness: Float): RecommendationRequestBuilder {
        this.max.instrumentalness = instrumentalness
        return this
    }

    fun setMaxKey(key: Int): RecommendationRequestBuilder {
        this.max.key = key
        return this
    }

    fun setMaxLiveness(liveness: Float): RecommendationRequestBuilder {
        this.max.liveness = liveness
        return this
    }

    fun setMaxLoudness(loudness: Float): RecommendationRequestBuilder {
        this.max.loudness = loudness
        return this
    }

    fun setMaxMode(mode: Int): RecommendationRequestBuilder {
        this.max.mode = mode
        return this
    }

    fun setMaxPopularity(popularity: Int): RecommendationRequestBuilder {
        this.max.popularity = popularity
        return this
    }

    fun setMaxSpeechiness(speechiness: Float): RecommendationRequestBuilder {
        this.max.speechiness = speechiness
        return this
    }

    fun setMaxTempo(tempo: Float): RecommendationRequestBuilder {
        this.max.tempo = tempo
        return this
    }

    fun setMaxTimeSignature(time_signature: Int): RecommendationRequestBuilder {
        this.max.timeSignature = time_signature
        return this
    }

    fun setMaxValence(valence: Float): RecommendationRequestBuilder {
        this.max.valence = valence
        return this
    }

    fun setMinAcousticness(acousticness: Float): RecommendationRequestBuilder {
        this.min.acousticness = acousticness
        return this
    }

    fun setMinDanceability(danceability: Float): RecommendationRequestBuilder {
        this.min.danceability = danceability
        return this
    }

    fun setMinDurationMs(duration_ms: Int): RecommendationRequestBuilder {
        this.min.durationMs = duration_ms
        return this
    }

    fun setMinEnergy(energy: Float): RecommendationRequestBuilder {
        this.min.energy = energy
        return this
    }

    fun setMinInstrumentalness(instrumentalness: Float): RecommendationRequestBuilder {
        this.min.instrumentalness = instrumentalness
        return this
    }

    fun setMinKey(key: Int): RecommendationRequestBuilder {
        this.min.key = key
        return this
    }

    fun setMinLiveness(liveness: Float): RecommendationRequestBuilder {
        this.min.liveness = liveness
        return this
    }

    fun setMinLoudness(loudness: Float): RecommendationRequestBuilder {
        this.min.loudness = loudness
        return this
    }

    fun setMinMode(mode: Int): RecommendationRequestBuilder {
        this.min.mode = mode
        return this
    }

    fun setMinPopularity(popularity: Int): RecommendationRequestBuilder {
        this.min.popularity = popularity
        return this
    }

    fun setMinSpeechiness(speechiness: Float): RecommendationRequestBuilder {
        this.min.speechiness = speechiness
        return this
    }

    fun setMinTempo(tempo: Float): RecommendationRequestBuilder {
        this.min.tempo = tempo
        return this
    }

    fun setMinTimeSignature(time_signature: Int): RecommendationRequestBuilder {
        this.min.timeSignature = time_signature
        return this
    }

    fun setMinValence(valence: Float): RecommendationRequestBuilder {
        this.min.valence = valence
        return this
    }

    fun setTargetAcousticness(acousticness: Float): RecommendationRequestBuilder {
        this.target.acousticness = acousticness
        return this
    }

    fun setTargetDanceability(danceability: Float): RecommendationRequestBuilder {
        this.target.danceability = danceability
        return this
    }

    fun setTargetDurationMs(duration_ms: Int): RecommendationRequestBuilder {
        this.target.durationMs = duration_ms
        return this
    }

    fun setTargetEnergy(energy: Float): RecommendationRequestBuilder {
        this.target.energy = energy
        return this
    }

    fun setTargetInstrumentalness(instrumentalness: Float): RecommendationRequestBuilder {
        this.target.instrumentalness = instrumentalness
        return this
    }

    fun setTargetKey(key: Int): RecommendationRequestBuilder {
        this.target.key = key
        return this
    }

    fun setTargetLiveness(liveness: Float): RecommendationRequestBuilder {
        this.target.liveness = liveness
        return this
    }

    fun setTargetLoudness(loudness: Float): RecommendationRequestBuilder {
        this.target.loudness = loudness
        return this
    }

    fun setTargetMode(mode: Int): RecommendationRequestBuilder {
        this.target.mode = mode
        return this
    }

    fun setTargetPopularity(popularity: Int): RecommendationRequestBuilder {
        this.target.popularity = popularity
        return this
    }

    fun setTargetSpeechiness(speechiness: Float): RecommendationRequestBuilder {
        this.target.speechiness = speechiness
        return this
    }

    fun setTargetTempo(tempo: Float): RecommendationRequestBuilder {
        this.target.tempo = tempo
        return this
    }

    fun setTargetTimeSignature(time_signature: Int): RecommendationRequestBuilder {
        this.target.timeSignature = time_signature
        return this
    }

    fun setTargetValence(valence: Float): RecommendationRequestBuilder {
        this.target.valence = valence
        return this
    }

    private fun pre(): String {
        if (!this.isFirst) {
            this.isFirst = false
            return "?"
        }
        return "&"
    }

    companion object {

        fun create(): RecommendationRequestBuilder {
            return RecommendationRequestBuilder()
        }
    }
}
