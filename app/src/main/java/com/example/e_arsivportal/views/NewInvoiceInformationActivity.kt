package com.example.e_arsivportal.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityNewInvoiceInformationBinding

class NewInvoiceInformationActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityNewInvoiceInformationBinding
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewInvoiceInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<TextView>(R.id.toolBarTittle).text = "Fatura Bilgileri"

        _binding.newInvoiceInformationPageNextButton.setOnClickListener() {
            val intent = Intent(this, NewInvoiceAddProductActivity::class.java)
            startActivity(intent)
        }

    }
}