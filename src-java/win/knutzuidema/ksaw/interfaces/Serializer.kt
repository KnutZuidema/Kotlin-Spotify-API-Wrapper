package win.knutzuidema.ksaw.interfaces

import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

interface Serializer {

    fun serialize(id: String) {
        try {
            val fos = FileOutputStream(id + ".ser")
            val oos = ObjectOutputStream(fos)
            oos.writeObject(this)
        } catch (ioe: IOException) {
            error("Couldn't create file")
        }

    }
}
