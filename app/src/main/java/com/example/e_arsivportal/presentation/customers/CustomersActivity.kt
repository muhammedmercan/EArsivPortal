package com.example.e_arsivportal.presentation.customers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biochakraastralterapi.adapters.CustomersAdapter
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityCustomersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomersActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: CustomersAdapter

    private lateinit var binding: ActivityCustomersBinding

    private lateinit var viewModel: CustomersViewModel
    private lateinit var context: Context



    override fun onRestart() {
        super.onRestart()
        //viewModel.getDataFromRoom()
        observeLiveData()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CustomersViewModel::class.java)


        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).text = "Müşteri Ekle"
        findViewById<TextView>(R.id.toolBarTittle).text = "Müşteriler"

        observeLiveData()

        binding.customersPageRecyclerview?.adapter = adapter
        binding.customersPageRecyclerview?.layoutManager = LinearLayoutManager(this)


        adapter.setOnItemClickListener {
            viewModel.deleteCustomer(it)
        }



        context = this



        //viewModel.getDataFromRoom()



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

            adapter.customerList = customerList

        }
        )
    }

    fun goToAddDataActivity() {
        val intent = Intent(this, AddCustomerActivity::class.java)
        startActivity(intent)
    }
}