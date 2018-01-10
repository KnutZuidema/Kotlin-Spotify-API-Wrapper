package win.knutzuidema.ksaw.datatypes.paging

import org.json.JSONObject
import java.io.Serializable

abstract class Paging(json: JSONObject) : Serializable {

    var href: String = json.getString("href")
        protected set
    var limit: Int = json.getInt("limit")
        protected set
    var next: String? = if (json.isNull("next")) null else json.getString("next")
        protected set
    var previous: String? = if (json.isNull("previous")) null else json.getString("previous")
        protected set
    var offset: Int = json.getInt("offset")
        protected set
    var total: Int = json.getInt("total")
        protected set

    companion object {
        private const val serialVersionUID: Long = 0x200
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Paging

        if (href != other.href) return false
        if (limit != other.limit) return false
        if (next != other.next) return false
        if (previous != other.previous) return false
        if (offset != other.offset) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = href.hashCode()
        result = 31 * result + limit
        result = 31 * result + (next?.hashCode() ?: 0)
        result = 31 * result + (previous?.hashCode() ?: 0)
        result = 31 * result + offset
        result = 31 * result + total
        return result
    }
}
