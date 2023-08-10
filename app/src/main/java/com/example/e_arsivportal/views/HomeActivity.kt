package com.example.e_arsivportal.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.e_arsivportal.databinding.ActivityHomeBinding
import com.example.e_arsivportal.utilities.DataHolder
import com.example.e_arsivportal.viewmodels.HomeViewModel
import com.example.e_arsivportal.viewmodels.LoginViewModel
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

            viewModel.exit(this)

            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }
    }
}