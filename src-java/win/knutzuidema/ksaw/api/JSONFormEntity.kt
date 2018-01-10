package win.knutzuidema.ksaw.api

import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.json.JSONObject

class JSONFormEntity : StringEntity {
    private var source: JSONObject = JSONObject()

    constructor(source: String) : super(source, ContentType.APPLICATION_JSON)

    constructor(source: JSONObject) : this(source.toString())

    private constructor() : super("", ContentType.APPLICATION_JSON)

    fun addParameter(name: String, value: Any?): JSONFormEntity {
        this.source.put(name, value)
        return this
    }

    fun build(): JSONFormEntity {
        return JSONFormEntity(this.source)
    }

    companion object {

        fun create(): JSONFormEntity {
            return JSONFormEntity()
        }
    }
}
