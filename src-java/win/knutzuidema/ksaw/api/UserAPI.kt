package win.knutzuidema.ksaw.api

import win.knutzuidema.ksaw.datatypes.User

object UserAPI {

    fun getUser(id: String): User {
        val request = API
                .requestBuilder("GET", "/users/" + id)
                .build()

        return User(API.getJSON(request)!!)
    }
}
