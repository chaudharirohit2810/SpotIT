package com.rohit2810.spotit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnEnableNotificationListener.setOnClickListener{
            openNotificationListenerSettings()
        }
    }

    private fun openNotificationListenerSettings() {
        val intent = Intent()
        intent.action = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
        startActivity(intent)
    }
}