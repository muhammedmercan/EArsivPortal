package com.example.e_arsivportal.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityHomeBinding
import com.example.e_arsivportal.databinding.ActivityNewInvoiceCustomerChoiceBinding

class NewInvoiceCustomerChoiceActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityNewInvoiceCustomerChoiceBinding
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewInvoiceCustomerChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<TextView>(R.id.toolBarLeftIcon).text = "İptal"

        _binding.newInvoiceCustomerChoicePageNextButton.setOnClickListener() {
            val intent = Intent(this, NewInvoiceInformationActivity::class.java)
            startActivity(intent)
        }







    }
}