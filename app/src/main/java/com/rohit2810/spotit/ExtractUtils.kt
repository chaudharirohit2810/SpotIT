package com.rohit2810.spotit

import android.service.notification.StatusBarNotification
import timber.log.Timber

fun extractSpotifyNotification(barNotification: StatusBarNotification): Array<String> {
    val extras = barNotification.notification.extras
    val notificationTitle: String = extras.getCharSequence("android.title").toString()
    val notificationText: String = extras.getCharSequence("android.text").toString()
    return arrayOf(notificationTitle, notificationText)
}


fun extractJioSaavnNotification(barNotification: StatusBarNotification): Array<String> {
    val extras = barNotification.notification.extras
    val notificationTitle: String = extras.getString("android.title", "Title")
    val notificationText: String = extras.getString("android.text", "Text")
    return arrayOf(notificationTitle, notificationText)
}

fun extractGaanaNotification(barNotification: StatusBarNotification): Array<String> {
    val extras = barNotification.notification.extras
    val notificationTitle: String = extras.getString("android.title", "Title")
    val notificationText: String = extras.getString("android.text", "Text")

    return arrayOf(notificationText, notificationTitle)
}

fun extractSpotifyLiteNotification(barNotification: StatusBarNotification): Array<String> {
    val extras = barNotification.notification.extras
    val notificationTitle: String = extras.getString("android.title", "Title")
    val notificationText: String = extras.getString("android.text", "Text")

    return arrayOf(notificationText, notificationTitle)
}
