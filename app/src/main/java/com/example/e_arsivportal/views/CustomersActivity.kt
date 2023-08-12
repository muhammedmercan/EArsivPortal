package com.example.e_arsivportal.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biochakraastralterapi.adapters.CustomersAdapter
import com.example.biochakraastralterapi.adapters.ProductsAdapter
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityCustomersBinding
import com.example.e_arsivportal.databinding.ActivityProductsBinding
import com.example.e_arsivportal.viewmodels.CustomersViewModel
import com.example.e_arsivportal.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomersBinding

    private lateinit var viewModel: CustomersViewModel
    private lateinit var context: Context


    val deleteButtonListener = object: CustomersAdapter.CustomViewHolderListener {


        override fun onCustomItemClicked(id: Int) {
            viewModel.deleteCustomer(id)

        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getDataFromRoom()
        observeLiveData()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).text = "Müşteri Ekle"
        findViewById<TextView>(R.id.toolBarTittle).text = "Müşteriler"

        context = this

        viewModel = ViewModelProvider(this).get(CustomersViewModel::class.java)


        //viewModel.getDataFromRoom()

        observeLiveData()

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<TextView>(R.id.toolBarRightIcon).setOnClickListener() {
            goToAddDataActivity()
        }


        binding.customersPageAddCustomerButton.setOnClickListener() {

            goToAddDataActivity()
        }
    }

    fun observeLiveData() {

        viewModel.liveData.observe(this, Observer { customerList ->

            customerList.let {
                println(customerList.toString())

                binding?.customersPageRecyclerview?.adapter =
                    this?.let { CustomersAdapter(customerList, it, deleteButtonListener) }

                binding?.customersPageRecyclerview?.layoutManager = LinearLayoutManager(this)

            }
        }
        )
    }

    fun goToAddDataActivity() {
        val intent = Intent(this, AddCustomerActivity::class.java)
        startActivity(intent)
    }
}