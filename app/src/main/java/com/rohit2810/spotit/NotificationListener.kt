package com.rohit2810.spotit

import android.content.Context
import android.media.AudioManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import timber.log.Timber


class NotificationListener : NotificationListenerService() {
    private lateinit var context: Context
    private lateinit var audioManager: AudioManager
    private var currentVolume = 50
    private var adFlag = false

    val PACKAGE_NAMES = arrayOf("com.spotify.music", "com.jio.media.jiobeats")
    val TITLES = arrayOf("Advertisement")

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        sbn?.let { barNotification ->

            val packageName = barNotification.packageName

            packageName?.let {
                if (it == "com.spotify.music") {
                    val notificationInfo = extractSpotifyNotification(barNotification)
                    turnOffMediaVolume(notificationInfo)
                }else if(it == "com.jio.media.jiobeats") {
                    val notificationInfo = extractJioSaavnNotification(barNotification)
                    turnOffMediaVolume(notificationInfo)
                }
            }



        }
    }

    private fun turnOffMediaVolume(notificationInfo: Array<String>) {
        if (TITLES.contains(notificationInfo[0])) {
            if (!adFlag) {
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
            }
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0)
            adFlag = true
        } else {
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