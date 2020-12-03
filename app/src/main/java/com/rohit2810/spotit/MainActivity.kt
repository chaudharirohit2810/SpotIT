package com.rohit2810.spotit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.main_activity_about_button.*
import kotlinx.android.synthetic.main.main_activity_notification_access_button.*


class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        createBottomSheetDialog()

        btnEnableNotificationListener.setOnClickListener {
            openNotificationListenerSettings()
        }

        btnHowItWorks.setOnClickListener {
            startActivity(Intent(this, HowItWorksActivity::class.java))
            overridePendingTransition(R.anim.right_in, R.anim.left_out)
        }

        btnRunningInBackground.setOnClickListener {
            bottomSheetDialog.show()
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
                "Enable notification access for SpotIt in order to detect advertisements in music streaming apps"
            tvNotificationAccessHeading.setTextColor(resources.getColor(R.color.errorColor))
        } else {
            ivNotificationAccess.setImageResource(R.drawable.icon_notification_access)
            tvNotificationAccessSubtitle.text = "To detect advertisements in music streaming apps"
            tvNotificationAccessHeading.setTextColor(resources.getColor(R.color.textHeadingColor))
        }
    }


    private fun checkNotificationListenerEnabled(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(this)
            .contains("com.rohit2810.spotit")
    }

    private fun createBottomSheetDialog() {
        val view: View = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_running_in_background, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(view)

        val btnBatterySettings = bottomSheetDialog.findViewById<TextView>(R.id.btnBatterySettings)
        btnBatterySettings?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://dontkillmyapp.com/"))
            startActivity(intent)
        }
    }
}