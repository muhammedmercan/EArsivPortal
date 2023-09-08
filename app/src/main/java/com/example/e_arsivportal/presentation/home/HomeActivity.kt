package com.example.e_arsivportal.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.e_arsivportal.databinding.ActivityHomeBinding
import com.example.e_arsivportal.presentation.customers.CustomersActivity
import com.example.e_arsivportal.presentation.incominginvoices.IncomingInvoicesActivity
import com.example.e_arsivportal.presentation.login.LoginActivity
import com.example.e_arsivportal.presentation.newinvoice.NewInvoiceCustomerChoiceActivity
import com.example.e_arsivportal.presentation.outgoinginvoices.OutgoingInvoicesActivity
import com.example.e_arsivportal.presentation.products.ProductsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {



    private lateinit var binding: ActivityHomeBinding

    private lateinit var viewModel: HomeViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)



        onClick()



    }

    private fun onClick() {

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

        binding.homePageOutgoingInvoiceCardView.setOnClickListener() {
            val intent = Intent(this, OutgoingInvoicesActivity::class.java)
            startActivity(intent)
        }

        binding.homePageIncomingCardView.setOnClickListener() {
            val intent = Intent(this, IncomingInvoicesActivity::class.java)
            startActivity(intent)
        }

        binding.homePageExitButton.setOnClickListener() {

            viewModel.exit()

            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}