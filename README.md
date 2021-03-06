<p align="center">
 
   <img src="spotit.png" alt="Logo" width="100" height="100">

  <h1 align="center">SpotIt</h3>

  <h3 align="center">
    Music Streaming Advertisement Muter
  </h3>
</p>

<p align="center">
  <img alt="GitHub top language" src="https://img.shields.io/badge/language-Kotlin-brightgreen">

  <a>
    <img alt="Made by" src="https://img.shields.io/badge/made%20by-Rohit%20Chaudhari-blueviolet">
  </a>

  <img alt="License" src="https://img.shields.io/badge/license-GPLv3-orange">
</p>

##  👨🏻‍💻 About The Project
SpotIt is a music streaming advertisement muter. It detects advertisements in music streaming apps using notifications and mutes them using AudioManager. It works with popular music streaming services like Spotify, JioSaavn and Gaana. 

## :package: Download
<a href="https://f-droid.org/en/packages/com.rohit2810.spotit/" target="blank">
    <img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png" height="75">
</a>

## :framed_picture: Screenshots
<div style="display:flex;">
<img src="Screenshots/2.jpg" width="150">
<img src="Screenshots/1.jpg" width="150">
</div>

## :bulb: How It Works
1. SpotIt listens for notifications from music streaming apps like Spotify, JioSaavn and Gaana using NotificationListenerService.
2. Then it searches for words like "advertisement", "sponsored ad" in the notification's data.
3. If the advertisement words are found then it mutes the music streaming audio (STREAM_MUSIC) of a device using AudioManager.

## :smiley: Privacy
1. The app does not collect any private data nor it requires any network permissions.
2. It uses user notifications to detect advertisements in music streaming services and does not <b>"hack"</b> protection measures of music streaming services

## :construction_worker: Issues and Feature requests
If you found a bug, or have an idea for new functionality, feel free to report it on the issue tracker.
