package com.rohit2810.spotit

import android.content.Context
import android.media.AudioManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import timber.log.Timber
import kotlin.math.max


class NotificationListener : NotificationListenerService() {
    private lateinit var context: Context
    private lateinit var audioManager: AudioManager
    private var currentVolume = 50
    private var adFlag = false

    val PACKAGE_NAMES = arrayOf("com.spotify.music", "com.jio.media.jiobeats", "com.spotify.lite")
    val TITLES = arrayOf("Advertisement", "-Sponsored Ad")

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        try {
            sbn?.let { barNotification ->
                val packageName = barNotification.packageName
                packageName?.let {

                    if (it == "com.spotify.music") {
                        val notificationInfo = extractSpotifyNotification(barNotification)
                        turnOffMediaVolume(notificationInfo)
                    } else if (it == "com.jio.media.jiobeats") {
                        val notificationInfo = extractJioSaavnNotification(barNotification)
                        turnOffMediaVolume(notificationInfo)
                    } else if (it == "com.spotify.lite") {
                        val notificationInfo = extractSpotifyLiteNotification(barNotification)
                        turnOffMediaVolume(notificationInfo)
                    }else if(it == "com.gaana") {
                        val notificationInfo = extractGaanaNotification(barNotification)
                        turnOffMediaVolume(notificationInfo)
                    }
                    logInfo(sbn)
                }
            }
        } catch (e: Exception) {
            Timber.d(e.localizedMessage)
        }
    }


    private fun logInfo(sbn: StatusBarNotification?) {
        var barNotification = sbn!!
        var package_name = barNotification.packageName
        val extras = barNotification.notification.extras
        val notificationTitle: String = extras.getString("android.title", "Title")
        val notificationText: String = extras.getString("android.text", "Text")
        Timber.d("Package Name: $package_name")
        Timber.d("Title: $notificationTitle")
        Timber.d("Text: $notificationText")
    }

    private fun turnOffMediaVolume(notificationInfo: Array<String>) {
        if (TITLES.contains(notificationInfo[0])) {
            if (!adFlag) {
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
            }
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
            adFlag = true
        } else {
            val tempVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
            if (tempVolume != currentVolume && tempVolume != 0) {
                currentVolume = tempVolume
            }
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0)
            adFlag = false
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Timber.d("Notification Removed")
        val pack = sbn?.packageName


        if (PACKAGE_NAMES.contains(pack)) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0)
        }
    }


}
