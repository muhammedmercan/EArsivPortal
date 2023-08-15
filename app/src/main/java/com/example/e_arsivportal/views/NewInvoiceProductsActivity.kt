package com.example.e_arsivportal.views

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biochakraastralterapi.adapters.NewInvoiceProductsAdapter
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityNewInvoiceProductsBinding
import com.example.e_arsivportal.models.CreateInvoiceModel
import com.example.e_arsivportal.models.InvoiceModel
import com.example.e_arsivportal.models.NewInvoiceProductModel
import com.example.e_arsivportal.utilities.DataHolder
import com.example.e_arsivportal.viewmodels.NewInvoiceProductsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewInvoiceProductsActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: NewInvoiceProductsAdapter

    private lateinit var binding: ActivityNewInvoiceProductsBinding

    val REQUEST_CODE_ADD_PRODUCT = 1

    var counter = 0

    var productList : MutableList<NewInvoiceProductModel> = mutableListOf()

    lateinit var  invoiceModel : InvoiceModel

    private lateinit var viewModel: NewInvoiceProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewInvoiceProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NewInvoiceProductsViewModel::class.java)


        init()
        observeLiveData()
        onClick()

        binding?.newInvoiceProductsPageRecyclerView?.adapter = adapter

        binding?.newInvoiceProductsPageRecyclerView?.layoutManager =
            LinearLayoutManager(this)

        findViewById<TextView>(R.id.toolBarTittle).text = "Mal ve Hizmetler"
        findViewById<TextView>(R.id.toolBarRightIcon).text = "Ekle"

        if (DataHolder.productList.value.isNullOrEmpty()) {
            val intent = Intent(this, NewInvoiceAddProductActivity::class.java)
            startActivity(intent)
        }

        counter+=1
    }

    fun init() {

        intent = intent

        invoiceModel = intent.getSerializableExtra("invoiceModel") as InvoiceModel

        adapter.productList = DataHolder.productList.value!!

    }

    fun save() {

        val gson = Gson()
        val json = gson.toJson(CreateInvoiceModel(invoiceModel,productList))
        println(json)

        viewModel.createInvoice(CreateInvoiceModel(invoiceModel,productList))

    }

    fun onClick() {

        adapter.setOnItemClickListener {
            DataHolder.productList.value?.removeAt(it)
        }

        binding.newInvoiceProductsPageSaveButton.setOnClickListener() {

            save()
        }

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            finish()
        }

        findViewById<TextView>(R.id.toolBarRightIcon).setOnClickListener() {
            val intent = Intent(this, NewInvoiceAddProductActivity::class.java)
            startActivity(intent)
        }
    }

    fun observeLiveData() {

        DataHolder.productList.observe(this, Observer {

            var rawTotal = 0.0
            var vat = 0.0
            var grandTotal = 0.0

            productList = it

            it.let {

                adapter.productList = it

                for (i in it) {
                    rawTotal += i.unitPrice * i.quantity
                }

                binding.newInvoiceProductsPageProductTotalServiceAmountTextView.text = rawTotal.toString()
                binding.newInvoiceProductsPageProductTotalVatAmountTextView.text = (rawTotal * 0.18).toString()
                binding.newInvoiceProductsPageProductTotalGrandTotalAmountTextView.text = (rawTotal + rawTotal * 0.18).toString()

            }    })

        viewModel.liveData.observe(this, Observer {


            Toast.makeText(this,"Fatura Oluşturuldu",Toast.LENGTH_SHORT).show()

            intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()

        })
}}