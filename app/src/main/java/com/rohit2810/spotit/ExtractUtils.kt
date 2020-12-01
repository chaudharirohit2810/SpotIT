package com.rohit2810.spotit

import android.service.notification.StatusBarNotification

fun extractSpotifyNotification(barNotification: StatusBarNotification): Array<String> {
    val extras = barNotification.notification.extras
    val tickerText = barNotification.notification.tickerText.toString()
    val notificationTitle: String = extras.getCharSequence("android.title").toString()
    val notificationText: String = extras.getCharSequence("android.text").toString()
    return arrayOf(tickerText, notificationTitle, notificationText)
}


fun extractJioSaavnNotification(barNotification: StatusBarNotification): Array<String> {
    val extras = barNotification.notification.extras
    val notificationTitle: String = extras.getString("android.title", "Title")
    val notificationText: String = extras.getString("android.text", "Text")

    return arrayOf(notificationTitle, notificationText)
}