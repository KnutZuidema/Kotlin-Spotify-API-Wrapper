package win.knutzuidema.ksaw.datatypes

import javafx.beans.property.*
import org.json.JSONObject
import win.knutzuidema.ksaw.interfaces.Serializer

import java.io.Serializable

class Device(json: JSONObject) : Serializable, Serializer {

    val id: StringProperty = SimpleStringProperty(json.getString("id"))
    val name: StringProperty = SimpleStringProperty(json.getString("name"))
    val type: StringProperty = SimpleStringProperty(json.getString("type"))
    val volume: IntegerProperty = SimpleIntegerProperty(json.getInt("volume_percent"))
    val isActive: BooleanProperty = SimpleBooleanProperty(json.getBoolean("is_active"))
    val isRestricted: BooleanProperty = SimpleBooleanProperty(json.getBoolean("is_restricted"))

    companion object {
        private const val serialVersionUID: Long = 0x500
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Device

        if (id != other.id) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (volume != other.volume) return false
        if (isActive != other.isActive) return false
        if (isRestricted != other.isRestricted) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + volume.hashCode()
        result = 31 * result + isActive.hashCode()
        result = 31 * result + isRestricted.hashCode()
        return result
    }
}