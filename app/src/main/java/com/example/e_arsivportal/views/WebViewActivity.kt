package com.example.e_arsivportal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.webkit.WebSettings
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityHomeBinding
import com.example.e_arsivportal.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityWebViewBinding
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings: WebSettings = binding.webView.getSettings()
        settings.allowFileAccess = true



        binding.webView.loadUrl(Environment.getExternalStorageDirectory().toString() + "/" + Environment.DIRECTORY_DOWNLOADS.toString() + "/name_file.html")
    }
}