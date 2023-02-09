package com.news.livenews.worldwidenews.view

import android.app.Activity
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.webkit.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.news.livenews.worldwidenews.R
import com.news.livenews.worldwidenews.Utils.Fun

class WebActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    var string: String? = null
    private val mActivity: Activity? = null
    private val progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_web)
        string = intent.getStringExtra("url")
        wevViewfunction()
        Fun(this)
        val adContainerView = findViewById<FrameLayout>(R.id.ad_view_container)
        Fun.showBannerAds(adContainerView, this)
    }

    private fun wevViewfunction() {
        webView = findViewById(R.id.webViewID)
        webView.setWebViewClient(browser())
        webView.setWebChromeClient(GoogleClient())
        webView.getSettings().javaScriptEnabled = true
        webView.getSettings().setSupportZoom(true)
        webView.loadUrl(string!!)
        webView.getSettings().javaScriptCanOpenWindowsAutomatically = true
        webView.getSettings().defaultFontSize
        webView.clearCache(true)
        webView.clearHistory()
        webView.getSettings().loadsImagesAutomatically = true
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        webView.setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            val request = DownloadManager.Request(Uri.parse(url))
            request.setMimeType(mimetype)
            val cookies = CookieManager.getInstance().getCookie(url)
            request.addRequestHeader("cookie", cookies)
            request.addRequestHeader("User-Agent", userAgent)
            request.setDescription("Downloading file...")
            request.setTitle(
                URLUtil.guessFileName(
                    url, contentDisposition,
                    mimetype
                )
            )
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalFilesDir(
                this@WebActivity,
                Environment.DIRECTORY_DOWNLOADS, ".pdf"
            )
            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
            Toast.makeText(
                applicationContext, "Downloading File",
                Toast.LENGTH_LONG
            ).show()
        })
        webView.setOnClickListener(View.OnClickListener { webView.loadUrl(string!!) })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.web_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goBack -> if (webView!!.canGoBack()) {
                webView!!.goBack()
            }
            R.id.goForward -> if (webView!!.canGoForward()) {
                webView!!.goForward()
            }
            R.id.reload -> webView!!.loadUrl(webView!!.url.toString())
            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Install now")
                val app_url = ""
                shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
            R.id.rate -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(""))
                startActivity(intent)
            }
            R.id.moreapp -> {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(""))
                startActivity(i)
            }
            R.id.exit -> {
                finish()
                System.exit(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class browser : WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    private inner class GoogleClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }
    }
}