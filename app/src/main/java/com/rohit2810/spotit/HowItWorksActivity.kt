package com.rohit2810.spotit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_how_it_works.*

class HowItWorksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_it_works)
        supportActionBar?.title = "About"
        tvPrivacyDetails.movementMethod = LinkMovementMethod.getInstance()
        tvGithubLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/chaudharirohit2810/SpotIT"))
            startActivity(intent)
        }
        
    }
}