package win.knutzuidema.ksaw.api

import win.knutzuidema.ksaw.interfaces.Serializer
import java.io.Serializable

object Config : Serializable, Serializer{
    var accessCode = "AQD8DgMwW6NBhQ8RZwqUQl6g0osvgJfCXf4Dk3UoXuSmExiDyGlRYUOLXhSgjsPmr41zdBgzaZb40HwGQOctsxm5pxKVdL5_enw9-a33PgtSVh4tSe65M9DTYXO5wuNSDZnTnU4SlfwX-yx1w8MXkuDND0Dqkao_ryRVGGTlWP74b7Gb8hnjIYm3f-xqqmUhNhUUIejtTcPG-GcQLqXUM0Ps1SPYchmuCUGAUoY9uupdnTgBLO9NAXQ15ZERnv_dSz6-sfp04idAciVauA97Yo2nObEdS-QLFjuutjp9KwjRYb47S7WuyBJdtQBvN9eQdoulJRssPUP1tUKhchey0wgmi4PJaHJEIK3_doYloakG9DQIwcjp3X-ZjUrmYttfiR4V8o1fqi6UQjLVea59RYFxG_O_nubwGuxaGx_VP9I5_Vb_Xzt_GbpTX39efC58iSj7BouY5rlw8AUGRPqEaBZkRFobG6Xk11ne2WtpAeZgnsZwK2DObOQdHRjvumImQxcOkJ3K_icfpCOO4iURsUDy2ZM1wjXBaze-sStQ74keZ9SC9YmYkfevayLUQ8n5EHDLbsMgaGGm4BlLl-OQXfEvNlkJSoA_tQ7KX1vS308Wpi4JIvVTgT552EjT-USwEjH_tcBO7c9gxx3Bf0nj1liKPigWxw"
        get(){
            if(field == ""){
                error("No value provided")
            }
            return field
        }
    var refreshToken = ""
        get(){
            if(field == ""){
                error("No value provided")
            }
            return field
        }
    var userAgent = ""
        get(){
            if(field == ""){
                error("No value provided")
            }
            return field
        }
    var clientID = ""
        get(){
            if(field == ""){
                error("No value provided")
            }
            return field
        }
    var clientSecret = ""
        get(){
            if(field == ""){
                error("No value provided")
            }
            return field
        }
    var redirectURL = ""
        get(){
            if(field == ""){
                error("No value provided")
            }
            return field
        }
    val scopes = mutableListOf<String>()
    var showDialog = false
    var useState = false
        private set
    var state = ""
        set(str){
            useState = true
            field = str
        }

    fun splitScopes(): String{
        val str = StringBuilder()
        for((i, s) in this.scopes.withIndex()){
            str.append(s)
            if(i < this.scopes.size - 1){
                str.append("+")
            }
        }
        return str.toString()
    }
}