package com.example.pdfviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpOnClickListener()
    }


    private fun setUpOnClickListener() {
        buttonWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivtiy::class.java)
            startActivity(intent)
        }
        buttonAssets.setOnClickListener {
            val intent = Intent(this, PdfViewActivity::class.java)
            intent.putExtra("ViewType", "assets")
            startActivity(intent)
        }
        buttonStorage.setOnClickListener {
            val intent = Intent(this, PdfViewActivity::class.java)
            intent.putExtra("ViewType", "storage")
            startActivity(intent)
        }
        buttonInternet.setOnClickListener {
            val intent = Intent(this, PdfViewActivity::class.java)
            intent.putExtra("ViewType", "internet")
            startActivity(intent)
        }
    }
}
