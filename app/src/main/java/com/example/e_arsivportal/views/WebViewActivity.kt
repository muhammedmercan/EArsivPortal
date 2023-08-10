package com.example.e_arsivportal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.webkit.WebSettings
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityHomeBinding
import com.example.e_arsivportal.databinding.ActivityWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings: WebSettings = binding.webView.getSettings()
        settings.allowFileAccess = true



        binding.webView.loadUrl(Environment.getExternalStorageDirectory().toString() + "/" + Environment.DIRECTORY_DOWNLOADS.toString() + "/name_file.html")
        println(Environment.getExternalStorageDirectory().toString() + "/" + Environment.DIRECTORY_DOWNLOADS.toString() + "/name_file.html")
    }
}