package win.knutzuidema.ksaw.api

import win.knutzuidema.ksaw.datatypes.Category
import win.knutzuidema.ksaw.datatypes.paging.PagingAlbum
import win.knutzuidema.ksaw.datatypes.paging.PagingCategory
import win.knutzuidema.ksaw.datatypes.paging.PagingPlaylist

object BrowseAPI {

    val featuredPlaylists: PagingPlaylist
        get() {
            val request = API
                    .requestBuilder("GET", "/browse/featured-playlists")
                    .build()

            return PagingPlaylist(API
                    .getJSON(request)!!
                    .getJSONObject("playlists"))
        }

    val newReleases: PagingAlbum
        get() {
            val request = API
                    .requestBuilder("GET", "/browse/new-releases")
                    .build()

            return PagingAlbum(API
                    .getJSON(request)!!
                    .getJSONObject("albums"))
        }

    val categoriesList: PagingCategory
        get() {
            val request = API
                    .requestBuilder("GET", "/browse/categories")
                    .build()

            return PagingCategory(API
                    .getJSON(request)!!
                    .getJSONObject("categories"))
        }

    fun getCategory(id: String): Category {
        val request = API
                .requestBuilder("GET", "/browse/categories/" + id)
                .build()

        return Category(API.getJSON(request)!!)
    }

    fun getCategoryPlaylists(id: String): PagingPlaylist {
        val request = API
                .requestBuilder("GET", "/browse/categories/$id/playlists")
                .build()

        return PagingPlaylist(API
                .getJSON(request)!!
                .getJSONObject("playlists"))
    }
}
