package com.example.e_arsivportal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityNewInvoiceAddProductBinding

class NewInvoiceAddProductActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityNewInvoiceAddProductBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewInvoiceAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        findViewById<TextView>(R.id.toolBarTittle).text = "Yeni Mal Hizmet"
        findViewById<TextView>(R.id.toolBarRightIcon).text = "Ekle"


    }
}