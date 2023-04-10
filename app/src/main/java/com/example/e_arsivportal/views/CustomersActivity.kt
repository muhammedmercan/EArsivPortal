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


class CustomersActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCustomersBinding
    private val binding get() = _binding!!

    private lateinit var viewModel: CustomersViewModel
    private lateinit var context: Context


    val deleteButtonListener = object: CustomersAdapter.CustomViewHolderListener {


        override fun onCustomItemClicked(id: Int) {
            viewModel.deleteCustomer(id,context)

        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getDataFromRoom(context)
        observeLiveData()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        viewModel = ViewModelProvider(this).get(CustomersViewModel::class.java)


        viewModel.getDataFromRoom(applicationContext)

        observeLiveData()


        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).text = "Ürün Ekle"
        findViewById<TextView>(R.id.toolBarTittle).text = "Ürünler"

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

                _binding?.customersPageRecyclerview?.adapter =
                    this?.let { CustomersAdapter(customerList, it, deleteButtonListener) }

                _binding?.customersPageRecyclerview?.layoutManager = LinearLayoutManager(this)

            }
        }
        )
    }

    fun goToAddDataActivity() {
        val intent = Intent(this, AddCustomerActivity::class.java)
        startActivity(intent)
    }
}