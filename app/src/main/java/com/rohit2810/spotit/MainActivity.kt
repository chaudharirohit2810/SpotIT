package com.rohit2810.spotit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        supportActionBar?.title = " "

        setContentView(R.layout.activity_main)

        
        btnEnableNotificationListener.setOnClickListener {
            openNotificationListenerSettings()
        }

        btnHowItWorks.setOnClickListener {
            startActivity(Intent(this, HowItWorksActivity::class.java))
        }

    }

    private fun openNotificationListenerSettings() {
        try {
            val intent = Intent()
            intent.action = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Please go to settings, Search for notification access and enable notification access for SpotIt",
                Toast.LENGTH_LONG
            ).show()
        }

    }


    override fun onResume() {
        super.onResume()
        if (!checkNotificationListenerEnabled()) {
            ivNotificationAccess.setImageResource(R.drawable.icon_notifications_access_disabled)
            tvNotificationAccessSubtitle.text =
                "Enable notification access for SpotIt in order to detect advertisements in streaming apps"
            tvNotificationAccessHeading.setTextColor(resources.getColor(R.color.errorColor))
        } else {
            ivNotificationAccess.setImageResource(R.drawable.icon_notification_access)
            tvNotificationAccessSubtitle.text = "To detect advertisements in streaming apps"
            tvNotificationAccessHeading.setTextColor(resources.getColor(R.color.textHeadingColor))
        }
    }


    private fun checkNotificationListenerEnabled(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(this)
            .contains("com.rohit2810.spotit")
    }
}