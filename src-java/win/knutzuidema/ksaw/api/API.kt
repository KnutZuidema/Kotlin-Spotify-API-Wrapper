package win.knutzuidema.ksaw.api

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.methods.RequestBuilder
import org.apache.http.impl.client.HttpClients
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStreamReader

object API {

    val API_URI = "https://api.spotify.com/v1"

    fun getResponse(request: HttpUriRequest): HttpResponse? {
        val httpClient = HttpClients.createDefault()

        return try {
            httpClient.execute(request)
        } catch (e: IOException) {
            null
        }

    }

    fun getJSONfromResponse(response: HttpResponse?): JSONObject? {
        try {
            InputStreamReader(response!!.entity.content).use { isr ->
                val string = StringBuilder()
                while (isr.ready()) {
                    string.append(isr.read().toChar())
                }
                println(string)
                return JSONObject(string.toString())
            }
        } catch (e: Exception) {
            println(response!!.statusLine)
            e.printStackTrace()
            return null
        }
    }

    fun getArrayFromResponse(response: HttpResponse): JSONArray? {
        try {
            InputStreamReader(response.entity.content).use { isr ->
                val string = StringBuilder()
                while (isr.ready()) {
                    string.append(isr.read().toChar())
                }
                return JSONArray(string.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun getJSON(request: HttpUriRequest): JSONObject? {
        return getJSONfromResponse(getResponse(request))
    }

    fun requestBuilder(method: String, uri: String): RequestBuilder {
        return RequestBuilder.create(method).setUri(API_URI + uri).addHeader(Authentication.bearerAuth())
    }

    fun toQueryString(collection: Collection<*>): String {
        val stringBuilder = StringBuilder()
        for ((i, element) in collection.withIndex()) {
            stringBuilder.append(element)
            if (i < collection.size - 1) {
                stringBuilder.append(',')
            }
        }
        return stringBuilder.toString()
    }
}