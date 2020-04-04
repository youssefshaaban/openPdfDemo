package com.example.pdfviewer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pdfviewer.utils.FileUtils
import kotlinx.android.synthetic.main.activity_pdf_view.*

class PdfViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        checkActionFromIntent(intent)
    }

    private fun checkActionFromIntent(intent: Intent?) {
        intent?.let {
            when(it.getStringExtra("ViewType")){
                "assets" -> {
                    showPdfFromAssets(FileUtils.getPdfNameFromAssets())
                }
                "storage" -> {
                    // perform action to show pdf from storage
                    selectPdfFromStorage()
                }
                "internet" -> {
                    // perform action to show pdf from the internet
                }
            }
        }
    }

    private fun showPdfFromAssets(pdfName: String) {
        pdfView.fromAsset(pdfName)
            .password(null) // if password protected, then write password
            .defaultPage(0) // set the default page to open
            .onPageError { page, _ ->
                Toast.makeText(
                    this@PdfViewActivity,
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }

    private fun selectPdfFromStorage() {
        Toast.makeText(this, "selectPDF", Toast.LENGTH_LONG).show()
        val browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type = "application/pdf"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(browseStorage, "Select PDF"), PDF_SELECTION_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedPdfFromStorage = data.data
            showPdfFromUri(selectedPdfFromStorage)
        }
    }

    private fun showPdfFromUri(uri: Uri?) {
        pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    companion object {
        private const val PDF_SELECTION_CODE = 99
    }
}
