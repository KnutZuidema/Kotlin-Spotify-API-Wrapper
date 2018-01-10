package win.knutzuidema.ksaw.api

class SearchQueryBuilder private constructor() {
    private var keywords = StringBuilder()
    private var exclusions = StringBuilder(" NOT ")
    private var alternatives = StringBuilder(" OR ")
    private var album = ""
    private var artist = ""
    private var track = ""
    private var year = ""
    private var genre = ""
    private var isNew = false
    private var isHipster = false

    fun addKeywords(keywords: String): SearchQueryBuilder {
        this.keywords.append(keywords.trim { it <= ' ' })
        return this
    }

    fun addExclusions(exclusions: String): SearchQueryBuilder {
        this.exclusions.append(exclusions.trim { it <= ' ' })
        return this
    }

    fun addAlternatives(alternatives: String): SearchQueryBuilder {
        this.alternatives.append(alternatives.trim { it <= ' ' })
        return this
    }

    fun setAlbum(album: String): SearchQueryBuilder {
        this.album = " album:" + album.trim { it <= ' ' }
        return this
    }

    fun setArtist(artist: String): SearchQueryBuilder {
        this.artist = " artist:" + artist.trim { it <= ' ' }
        return this
    }

    fun setTrack(track: String): SearchQueryBuilder {
        this.track = " track:" + track.trim { it <= ' ' }
        return this
    }

    fun setYear(year: String): SearchQueryBuilder {
        this.year = " year:" + year.trim { it <= ' ' }
        return this
    }

    fun setYear(year: Int): SearchQueryBuilder {
        return setYear(year.toString())
    }

    fun setYear(from: String, to: String): SearchQueryBuilder {
        this.year = " year:" + from.trim { it <= ' ' } + "-" + to.trim { it <= ' ' }
        return this
    }

    fun setYear(from: Int, to: Int): SearchQueryBuilder {
        return setYear(from.toString(), to.toString())
    }

    fun setNew(bool: Boolean): SearchQueryBuilder {
        this.isNew = bool
        return this
    }

    fun setHipster(bool: Boolean): SearchQueryBuilder {
        this.isHipster = bool
        return this
    }

    fun setFieldGenre(genre: String): SearchQueryBuilder {
        this.genre = "genre:" + genre.trim { it <= ' ' }
        return this
    }

    fun build(): String {
        val keywords = this.keywords.toString()
        val alternatives = this.alternatives.toString()
        val exclusions = this.exclusions.toString()
        this.keywords = StringBuilder()
        this.alternatives = StringBuilder()
        this.exclusions = StringBuilder()

        return keywords +
                alternatives +
                exclusions +
                album +
                artist +
                track +
                year +
                genre +
                (if (isHipster) " tag:hipster" else "") +
                if (isNew) " tag:new" else ""
    }

    companion object {
        private var instance: SearchQueryBuilder? = null
            get() {
                if (field == null) field = SearchQueryBuilder()
                return field
            }
    }
}
