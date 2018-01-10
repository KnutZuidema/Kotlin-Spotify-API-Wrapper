package win.knutzuidema.ksaw.datatypes

import javafx.beans.property.*
import org.json.JSONObject
import win.knutzuidema.ksaw.api.PlayerAPI
import win.knutzuidema.ksaw.enums.RepeatState

import java.time.LocalDateTime

class Player(json: JSONObject) {
    val activeDevice: ObjectProperty<Device>
    val repeatState: ObjectProperty<RepeatState>
    val isShuffeling: BooleanProperty
    val context: ObjectProperty<Context>
    val timestamp: IntegerProperty
    val progress: IntegerProperty
    val isPlaying: BooleanProperty
    val currentlyPlaying: ObjectProperty<Track>
    val updateThread: Thread
    var refreshRate = 1000
    private val lastUpdated: ObjectProperty<LocalDateTime>

    init {
        this.activeDevice = SimpleObjectProperty(Device(json.getJSONObject("device")))
        this.repeatState = SimpleObjectProperty(RepeatState.valueOf(json.getString("repeat_state").toUpperCase()))
        this.isShuffeling = SimpleBooleanProperty(json.getBoolean("shuffle_state"))
        this.context = SimpleObjectProperty(Context(json.getJSONObject("context")))
        this.timestamp = SimpleIntegerProperty(json.getInt("timestamp"))
        this.progress = SimpleIntegerProperty(json.getInt("progress_ms"))
        this.isPlaying = SimpleBooleanProperty(json.getBoolean("is_playing"))
        this.currentlyPlaying = SimpleObjectProperty(Track(json.getJSONObject("item")))
        this.lastUpdated = SimpleObjectProperty(LocalDateTime.now())
        val task = {
            while (!Thread.currentThread().isInterrupted) {
                val update = PlayerAPI.player
                val updateDevice = update.activeDevice.value
                val thisDevice = this.activeDevice.value
                thisDevice.volume.value = updateDevice.volume.value
                thisDevice.id.value = updateDevice.id.value
                thisDevice.name.value = updateDevice.name.value
                thisDevice.type.value = updateDevice.type.value
                thisDevice.isRestricted.value = updateDevice.isRestricted.value
                thisDevice.isActive.value = updateDevice.isActive.value
                this.repeatState.value = update.repeatState.value
                this.isShuffeling.value = update.isShuffeling.value
                this.context.value = update.context.value
                this.timestamp.value = update.timestamp.value
                this.progress.value = update.progress.value
                this.isPlaying.value = update.isPlaying.value
                this.currentlyPlaying.value = update.currentlyPlaying.value
                this.lastUpdated.value = LocalDateTime.now()
                Thread.sleep(this.refreshRate.toLong())
            }
        }
        this.updateThread = Thread(task)
        updateThread.isDaemon = true
    }

    fun keepSynchronized(doUpdate: Boolean) {
        if (!doUpdate) {
            if (updateThread.isAlive) updateThread.interrupt()
        } else {
            if (updateThread.isAlive) {
                updateThread.interrupt()
            }
            updateThread.start()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (activeDevice != other.activeDevice) return false
        if (repeatState != other.repeatState) return false
        if (isShuffeling != other.isShuffeling) return false
        if (context != other.context) return false
        if (timestamp != other.timestamp) return false
        if (progress != other.progress) return false
        if (isPlaying != other.isPlaying) return false
        if (currentlyPlaying != other.currentlyPlaying) return false
        if (updateThread != other.updateThread) return false
        if (refreshRate != other.refreshRate) return false
        if (lastUpdated != other.lastUpdated) return false

        return true
    }

    override fun hashCode(): Int {
        var result = activeDevice.hashCode()
        result = 31 * result + repeatState.hashCode()
        result = 31 * result + isShuffeling.hashCode()
        result = 31 * result + context.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + progress.hashCode()
        result = 31 * result + isPlaying.hashCode()
        result = 31 * result + currentlyPlaying.hashCode()
        result = 31 * result + updateThread.hashCode()
        result = 31 * result + refreshRate
        result = 31 * result + lastUpdated.hashCode()
        return result
    }


}
