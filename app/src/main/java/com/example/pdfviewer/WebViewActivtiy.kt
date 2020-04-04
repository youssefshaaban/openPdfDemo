package com.example.pdfviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.pdfviewer.utils.FileUtils
import kotlinx.android.synthetic.main.activity_web_view_activtiy.*

class WebViewActivtiy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_activtiy)
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true
        val url = FileUtils.getPdfUrl()
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
    }
}
