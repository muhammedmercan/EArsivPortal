package com.example.e_arsivportal.presentation.customers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityAddCustomerBinding
import com.example.e_arsivportal.domain.model.CustomerModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCustomerBinding

    private lateinit var viewModel: AddCustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddCustomerViewModel::class.java)

        var customer: CustomerModel? = intent.getSerializableExtra("customer") as? CustomerModel


        if (customer!= null) {
            binding.addCustomerPageTcTextInputLayout.editText?.setText(customer.vknTckn)
            binding.addCustomerPageTitleTextInputLayout.editText?.setText(customer.title)
            binding.addCustomerPageNameTextInputLayout.editText?.setText(customer.name)
            binding.addCustomerPageSurnameTextInputLayout.editText?.setText(customer.surname)
            binding.addCustomerPageTaxDepartmentInputLayout.editText?.setText(customer.taxDepartment)
            binding.addCustomerPageAdressInputLayout.editText?.setText(customer.adress)

        }

        init()
        setButtons()
        changeListener()

    }

    fun changeListener() {

        binding.addCustomerPageTcTextInputLayout.editText?.doAfterTextChanged {
            checkEmpty(binding.addCustomerPageTcTextInputLayout, it.isNullOrEmpty())
        }

        binding.addCustomerPageAdressInputLayout.editText?.doAfterTextChanged {
            checkEmpty(binding.addCustomerPageAdressInputLayout, it.isNullOrEmpty())
        }

    }

    fun checkEmpty(layout: TextInputLayout, boolean: Boolean) {

        layout.error = "Boş bırakamazsınız"
        layout.isErrorEnabled = boolean

    }

    fun setButtons() {

        binding.addCustomerPageSaveButton.setOnClickListener() {
            if (binding.addCustomerPageTcTextInputLayout.editText?.text.isNullOrEmpty()) {
                checkEmpty(binding.addCustomerPageTcTextInputLayout,true)

            }

            if (binding.addCustomerPageTaxDepartmentInputLayout.editText?.text.isNullOrEmpty()) {
                checkEmpty(binding.addCustomerPageTaxDepartmentInputLayout,true)

            }

            else {

                saveData()
            }
        }

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            onBackPressedDispatcher.onBackPressed()

        }
    }

    fun init() {

        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).visibility = View.GONE
        findViewById<TextView>(R.id.toolBarTittle).text = "Müşteri Ekle"

    }

    fun saveData() {


        var customer: CustomerModel? = intent.getSerializableExtra("customer") as? CustomerModel


        if (customer != null) {
            println(customer.id)
            customer.vknTckn = binding.addCustomerPageTcTextInputLayout.editText?.text.toString()
            customer.title =  binding.addCustomerPageTitleTextInputLayout.editText?.text.toString()
            customer.name = binding.addCustomerPageNameTextInputLayout.editText?.text.toString()
            customer.surname = binding.addCustomerPageSurnameTextInputLayout.editText?.text.toString()
            customer.taxDepartment = binding.addCustomerPageTaxDepartmentInputLayout.editText?.text.toString()
            customer.adress = binding.addCustomerPageAdressInputLayout.editText?.text.toString()



            viewModel.updateDataInRoom(
                customer
            )
            observeLiveData()
        }


        else {
            viewModel.saveDataToRoom(
                CustomerModel(
                    binding.addCustomerPageTcTextInputLayout.editText?.text.toString(),
                    binding.addCustomerPageTitleTextInputLayout.editText?.text.toString(),
                    binding.addCustomerPageNameTextInputLayout.editText?.text.toString(),
                    binding.addCustomerPageSurnameTextInputLayout.editText?.text.toString(),
                    binding.addCustomerPageTaxDepartmentInputLayout.editText?.text.toString(),
                    binding.addCustomerPageAdressInputLayout.editText?.text.toString()
                    ),
            )
            observeLiveData()
        }


    }

    fun observeLiveData() {

        viewModel.liveData.observe(this, Observer { productList ->

            productList.let {

                println(it)

                onBackPressedDispatcher.onBackPressed()

            }
        }
        )

        viewModel.liveDataUpdate.observe(this, Observer {

            it.let {

                println(it)
                onBackPressedDispatcher.onBackPressed()

            }
        }
        )


    }
}