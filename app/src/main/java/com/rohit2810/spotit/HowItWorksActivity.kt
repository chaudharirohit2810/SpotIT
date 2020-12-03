package com.rohit2810.spotit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_how_it_works.*

class HowItWorksActivity : AppCompatActivity() {

    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_it_works)
        supportActionBar?.show()
        supportActionBar?.title = "About"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createBottomSheetDialog()

        btnSourceCode.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/chaudharirohit2810/SpotIT"))
            startActivity(intent)
        }

        btnLicense.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gnu.org/licenses/gpl-3.0.en.html"))
            startActivity(intent)
        }


        btnPrivacy.setOnClickListener {
            bottomSheetDialog.show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.finish()
        return super.onOptionsItemSelected(item)
    }

    private fun createBottomSheetDialog() {
        val view: View = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_privacy, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(view)
    }
}