package win.knutzuidema.ksaw

import javafx.embed.swing.JFXPanel
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import win.knutzuidema.ksaw.api.Config
import win.knutzuidema.ksaw.api.MeAPI
import win.knutzuidema.ksaw.enums.TimeFrame
import java.util.*


fun main(args: Array<String>){
    Config.clientID = "93914c11455b48129810b4d273d4dbe6"
    Config.clientSecret = "44c9337ba3d84e3a8a2cd416fa78bd9b"
    Config.redirectURL = "https://www.spotify.com"
    val scopes = listOf("playlist-read-private", "playlist-read-collaborative",
            "playlist-modify-public", "playlist-modify-private", "user-follow-modify",
            "user-follow-read", "user-library-read", "user-library-modify",
            "user-read-private", "user-read-birthdate", "user-read-email",
            "user-top-read", "user-read-playback-state", "user-modify-playback-state",
            "user-read-currently-playing", "user-read-recently-played")
    Config.scopes.addAll(scopes)
    Config.userAgent = "ksawtest//v0.1: ksawtest by Knut Zuidema"
    Config.refreshToken = "AQDx3ItUysyaSgYTpUMb4oD7TaLdHy6WxfP33CUxPMluqNGx3lIqmMC7V9vuZeF0u0RBuJWC5F5TJlXtMgst7SLO80T9CqyUQW1MNBfQMAdGgp9hdOGvOMtEaTeLuvMZJtw"

    val jfx = JFXPanel()
    val topTracks = MeAPI.getTopTracks(TimeFrame.LONG_TERM)
    do {
        for ((index, track) in topTracks.items.withIndex()) {
            println("${index + 1}: ${track.name}")
        }
        println("${topTracks.items.size}: exit")
        println("Choose song: ")
        val x = Scanner(System.`in`).nextInt()
        val player = MediaPlayer(Media(topTracks.items[x-1].complete().previewURL))
        player.play()
        while(player.status == MediaPlayer.Status.PLAYING){}
    } while(x != topTracks.items.size)
}