package com.example.e_arsivportal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityAddProductBinding
import com.example.e_arsivportal.models.ProductModel
import com.example.e_arsivportal.viewmodels.AddProductViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding


    private lateinit var viewModel: AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)

        var product: ProductModel? = intent.getSerializableExtra("product") as? ProductModel


        if (product!= null) {
            binding.addProductPageProductNameTextInputLayout.editText?.setText(product.name)
            binding.addProductPageUnitAutoCompleteTextView.setText(product.unit)
            binding.addProductPagePriceTextInputLayout.editText?.setText(product.price.toString())
            binding.addProductPageVatRateAutoCompleteView.setText(product.vatRate.toString())

        }

        init()
        setButtons()
        changeListener()

    }

    fun changeListener() {

        binding.addProductPageProductNameTextInputLayout.editText?.doAfterTextChanged {
            checkEmpty(binding.addProductPageProductNameTextInputLayout, it.isNullOrEmpty())
        }

        binding.addProductPagePriceTextInputLayout.editText?.doAfterTextChanged {
            checkEmpty(binding.addProductPagePriceTextInputLayout, it.isNullOrEmpty())
        }
    }

    fun checkEmpty(layout: TextInputLayout, boolean: Boolean) {

        layout.error = "Boş bırakamazsınız"
        layout.isErrorEnabled = boolean

    }

    fun setButtons() {

        binding.addProductPageSaveButton.setOnClickListener() {
            if (binding.addProductPageProductNameTextInputLayout.editText?.text.isNullOrEmpty()) {
                checkEmpty(binding.addProductPageProductNameTextInputLayout,true)

            }

            if (binding.addProductPagePriceTextInputLayout.editText?.text.isNullOrEmpty()) {
                checkEmpty(binding.addProductPagePriceTextInputLayout,true)

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

        var units = arrayListOf("Gün", "Ay", "Yıl", "Saat", "Dakika", "Saniye", "Adet", "Paket", "Kutu", "mg" , "g", "kg", "lt", "ton", "Net Ton", "Gross Ton", "mm", "cm", "m",
            "km", "ml", "mm3", "cm2", "m2", "m3", "kJ", "cl", "KARAT", "KWH", "MWH", "Ton baş. taşıma kap.", "Brüt kalori", "1000 lt", "Saf alkol lt", "kg.m2", "Hücret adet", "Çift", "1000 m3",
            "Set", "1000 adet", "SCM", "NCM", "mmBTU", "CM³", "Düzine", "dm2", "dm", "ha", "Metretül (LM)")

        var vatRates = arrayListOf(0,1,8,18)

        val unitsAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.select_dialog_item, units)
        binding.addProductPageUnitAutoCompleteTextView.setAdapter(unitsAdapter)
        binding.addProductPageUnitAutoCompleteTextView.setText("Adet",false)

        val vatRatesAdapter: ArrayAdapter<Int> = ArrayAdapter<Int>(this,
            android.R.layout.select_dialog_item, vatRates)
        binding.addProductPageVatRateAutoCompleteView.setAdapter(vatRatesAdapter)
        binding.addProductPageVatRateAutoCompleteView.setText("18",false)


        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).visibility = View.GONE
        findViewById<TextView>(R.id.toolBarTittle).text = "Ürün Ekle"

    }

    fun saveData() {


        var product: ProductModel? = intent.getSerializableExtra("product") as? ProductModel


        if (product != null) {
            println(product.id)
            product.name = binding.addProductPageProductNameTextInputLayout.editText?.text.toString()
            product.price = binding.addProductPagePriceTextInputLayout.editText?.text.toString().toFloat()
            product.unit =  binding.addProductPageUnitTextInputLayout.editText?.text.toString()
            product.vatRate = binding.addProductPageVatRateTextInputLayout.editText?.text.toString().toInt()



            viewModel.updateDataInRoom(
                product
            )
            observeLiveData()
        }


        else {
            viewModel.saveDataToRoom(
                ProductModel(
                    binding.addProductPageProductNameTextInputLayout.editText?.text.toString(),
                    binding.addProductPageUnitTextInputLayout.editText?.text.toString(),
                    binding.addProductPagePriceTextInputLayout.editText?.text.toString().toFloat(),
                    binding.addProductPageVatRateTextInputLayout.editText?.text.toString().toInt()
                )
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