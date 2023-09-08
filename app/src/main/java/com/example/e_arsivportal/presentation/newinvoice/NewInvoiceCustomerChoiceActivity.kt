package com.example.e_arsivportal.presentation.newinvoice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityNewInvoiceCustomerChoiceBinding
import com.example.e_arsivportal.domain.model.InvoiceModel
import com.example.e_arsivportal.domain.model.RecipientDataModel
import com.example.e_arsivportal.util.DataHolder
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewInvoiceCustomerChoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewInvoiceCustomerChoiceBinding

    private var invoiceModel = InvoiceModel()

    private lateinit var viewModel: NewInvoiceCustomerChoiceViewModel

    private lateinit var recipientData: RecipientDataModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewInvoiceCustomerChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<TextView>(R.id.toolBarLeftIcon).text = "İptal"

        viewModel = ViewModelProvider(this).get(NewInvoiceCustomerChoiceViewModel::class.java)

        changeListener()
        onClick()
        observeLiveData()
        DataHolder.productList.value = mutableListOf()


        binding.newInvoiceCustomerChoicePageSearchButton.setOnClickListener() {

            viewModel.getDataFromApi(binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text.toString())
        }
    }

    fun changeListener() {

        binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.doAfterTextChanged {
            checkEmpty(binding.newInvoiceCustomerChoicePageTcTextInputLayout, it.isNullOrEmpty())
        }

        binding.newInvoiceCustomerChoicePageTitleTextInputLayout.editText?.doAfterTextChanged {

            if (binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text?.length == 10) {
                checkEmpty(binding.newInvoiceCustomerChoicePageTitleTextInputLayout, it.isNullOrEmpty())

            }
        }

        binding.newInvoiceCustomerChoicePageNameTextInputLayout.editText?.doAfterTextChanged {
            if (binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text?.length == 11) {
                checkEmpty(binding.newInvoiceCustomerChoicePageNameTextInputLayout, it.isNullOrEmpty())

            }
        }

        binding.newInvoiceCustomerChoicePageSurnameTextInputLayout.editText?.doAfterTextChanged {
            if (binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text?.length == 11) {
                checkEmpty(binding.newInvoiceCustomerChoicePageSurnameTextInputLayout, it.isNullOrEmpty())

            }
        }

    }

    fun checkEmpty(layout: TextInputLayout, boolean: Boolean) {

        layout.error = "Boş bırakamazsınız"
        layout.isErrorEnabled = boolean

    }

    fun onClick() {

        binding.newInvoiceCustomerChoicePageNextButton.setOnClickListener() {
            next()


        }

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {

            finish()

        }

        findViewById<TextView>(R.id.toolBarRightIcon).setOnClickListener() {
            next()

        }
    }

    fun next() {

        var counter = 0



        if (binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text.isNullOrEmpty()) {
            checkEmpty(binding.newInvoiceCustomerChoicePageTcTextInputLayout,true)

        }

        else if (binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text?.length!! < 10) {

            checkEmpty(binding.newInvoiceCustomerChoicePageTcTextInputLayout,true)

        }

        else if (binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text?.length == 11) {

            if (binding.newInvoiceCustomerChoicePageNameTextInputLayout.editText?.text.isNullOrEmpty()) {
                checkEmpty(binding.newInvoiceCustomerChoicePageNameTextInputLayout,true)
                counter+=1

            }

            if (binding.newInvoiceCustomerChoicePageSurnameTextInputLayout.editText?.text.isNullOrEmpty()) {
                checkEmpty(binding.newInvoiceCustomerChoicePageSurnameTextInputLayout,true)
                counter+=1

            }

            if (counter == 0) {

                invoiceModel.vknTckn = binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text.toString()
                invoiceModel.aliciUnvan = binding.newInvoiceCustomerChoicePageTitleTextInputLayout.editText?.text.toString()
                invoiceModel.aliciAdi = binding.newInvoiceCustomerChoicePageNameTextInputLayout.editText?.text.toString()
                invoiceModel.aliciSoyadi = binding.newInvoiceCustomerChoicePageSurnameTextInputLayout.editText?.text.toString()
                invoiceModel.vergiDairesi = binding.newInvoiceCustomerChoicePageTaxDepartmentInputLayout.editText?.text.toString()
                invoiceModel.adres = binding.newInvoiceCustomerChoicePageAdressInputLayout.editText?.text.toString()


                val intent = Intent(this, NewInvoiceInformationActivity::class.java)
                intent.putExtra("invoiceModel",invoiceModel)
                startActivity(intent)            }

        }

        else if (binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text?.length == 10) {

            if (binding.newInvoiceCustomerChoicePageTitleTextInputLayout.editText?.text.isNullOrEmpty()) {
                checkEmpty(binding.newInvoiceCustomerChoicePageTitleTextInputLayout,true)

            }
            else {

                invoiceModel.vknTckn = binding.newInvoiceCustomerChoicePageTcTextInputLayout.editText?.text.toString()
                invoiceModel.aliciUnvan = binding.newInvoiceCustomerChoicePageTitleTextInputLayout.editText?.text.toString()
                invoiceModel.aliciAdi = binding.newInvoiceCustomerChoicePageNameTextInputLayout.editText?.text.toString()
                invoiceModel.aliciSoyadi = binding.newInvoiceCustomerChoicePageSurnameTextInputLayout.editText?.text.toString()
                invoiceModel.vergiDairesi = binding.newInvoiceCustomerChoicePageTaxDepartmentInputLayout.editText?.text.toString()
                invoiceModel.adres = binding.newInvoiceCustomerChoicePageAdressInputLayout.editText?.text.toString()


                val intent = Intent(this, NewInvoiceInformationActivity::class.java)
                intent.putExtra("invoiceModel",invoiceModel)
                startActivity(intent)            }
        }




    }


    fun observeLiveData() {

        viewModel.liveData.observe(this, Observer { data ->

            recipientData = data

            binding.newInvoiceCustomerChoicePageTitleTextInputLayout.editText?.setText(recipientData.unvan)
            binding.newInvoiceCustomerChoicePageNameTextInputLayout.editText?.setText(recipientData.adi)
            binding.newInvoiceCustomerChoicePageSurnameTextInputLayout.editText?.setText(
                recipientData.soyadi
            )
            binding.newInvoiceCustomerChoicePageTaxDepartmentInputLayout.editText?.setText(
                recipientData.vergiDairesi
            )



        }
        )
    }}
