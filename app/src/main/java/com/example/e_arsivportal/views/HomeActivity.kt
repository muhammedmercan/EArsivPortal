package com.example.e_arsivportal.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_arsivportal.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {



    private lateinit var _binding: ActivityHomeBinding
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homePageCreateNewInvoiceCardView.setOnClickListener() {

            val intent = Intent(this, NewInvoiceCustomerChoiceActivity::class.java)
            startActivity(intent)
        }

        binding.homePageProductsCardView.setOnClickListener() {

            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        binding.homePageCustomersCardView.setOnClickListener() {

            val intent = Intent(this, CustomersActivity::class.java)
            startActivity(intent)
        }

        binding.homePageIncomingCardView.setOnClickListener() {
            val intent = Intent(this, IncomingInvoicesActivity::class.java)
            startActivity(intent)
        }

    }
}