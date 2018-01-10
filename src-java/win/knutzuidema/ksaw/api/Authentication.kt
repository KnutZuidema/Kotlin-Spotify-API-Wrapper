package win.knutzuidema.ksaw.api

import org.apache.http.Header
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader
import org.apache.http.message.BasicNameValuePair
import org.json.JSONObject
import java.io.InputStreamReader
import java.util.*

object Authentication {

    private val refreshToken: String?
        get() {
            val httpClient = HttpClients.createDefault()

            val post = HttpPost("https://accounts.spotify.com/api/token")

            post.addHeader("User-Agent", Config.userAgent)
            post.addHeader(basicAuth())

            val nvps = ArrayList<NameValuePair>()
            nvps.add(BasicNameValuePair("grant_type", "authorization_code"))
            nvps.add(BasicNameValuePair("code", Config.accessCode))
            nvps.add(BasicNameValuePair("redirect_uri", Config.redirectURL))

            try {
                post.entity = UrlEncodedFormEntity(nvps, "UTF-8")

                val response = httpClient.execute(post)

                println(response.statusLine)
                val isr = InputStreamReader(response.entity.content)

                val stringResponse = StringBuilder()
                while (isr.ready()) {
                    stringResponse.append(isr.read().toChar())
                }
                println(stringResponse)
                val json = JSONObject(stringResponse.toString())

                return json.getString("refresh_token")
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

        }

    val accessToken: String?
        get() {
            val httpClient = HttpClients.createDefault()

            val post = HttpPost("https://accounts.spotify.com/api/token")

            post.addHeader(basicAuth())

            val nvps = ArrayList<NameValuePair>(2)
            nvps.add(BasicNameValuePair("grant_type", "refresh_token"))
            nvps.add(BasicNameValuePair("refresh_token",
                    try {
                        Config.refreshToken
                    } catch (e: Exception){
                        Config.refreshToken = refreshToken!!
                        Config.refreshToken
                    }))

            try {
                post.entity = UrlEncodedFormEntity(nvps)

                val response = httpClient.execute(post)

                val isr = InputStreamReader(response.entity.content)

                val stringResponse = StringBuilder()
                while (isr.ready()) {
                    stringResponse.append(isr.read().toChar())
                }
                val json = JSONObject(stringResponse.toString())

                return json.getString("access_token")
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

        }

    val authenticationURL: String
        get(){
            val strb = StringBuilder()
            strb.append("https://accounts.spotify.com/authorize/?client_id=${Config.clientID}")
            strb.append("&response_type=code")
            strb.append("&redirect_uri=${Config.redirectURL}")
            strb.append(if(Config.useState) "&state=${Config.state}" else "")
            strb.append("&scope=${Config.splitScopes()}")
            strb.append("&show_dialog=${Config.showDialog}")
            return strb.toString()
        }

    fun bearerAuth(): Header {
        return BasicHeader("Authorization", "Bearer " + accessToken!!)
    }

    fun basicAuth(): Header {
        return BasicHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((Config.clientID + ":" + Config.clientSecret).toByteArray()))
    }
}
