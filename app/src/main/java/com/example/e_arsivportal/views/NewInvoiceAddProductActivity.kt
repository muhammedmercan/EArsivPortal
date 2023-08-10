package com.example.e_arsivportal.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityNewInvoiceAddProductBinding
import com.example.e_arsivportal.models.InvoiceModel
import com.example.e_arsivportal.models.NewInvoiceProductModel
import com.example.e_arsivportal.models.ProductModel
import com.example.e_arsivportal.utilities.DataHolder
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewInvoiceAddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewInvoiceAddProductBinding

    private var product = NewInvoiceProductModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewInvoiceAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<TextView>(R.id.toolBarTittle).text = "Yeni Mal Hizmet"
        findViewById<TextView>(R.id.toolBarRightIcon).text = "Kaydet"





        /*
        val intent = Intent(this, NewInvoiceInformationActivity::class.java)
        intent.putExtra("invoiceModel",invoiceModel)
        startActivity(intent)

         */

        onClick()


        init()

    }

    fun save() {

        product.name = binding.newInvoiceAddProductPageProductNameTextInputLayout.editText?.text.toString()

        if (!binding.newInvoiceAddProductPageQuantityTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            product.quantity = binding.newInvoiceAddProductPageQuantityTextInputLayout.editText?.text.toString().toInt()

        }
        //product.unit = binding.newInvoiceAddProductPageUnitTextInputLayout.editText?.text.toString()

        if (!binding.newInvoiceAddProductPageUnitPriceTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            product.unitPrice = binding.newInvoiceAddProductPageUnitPriceTextInputLayout.editText?.text.toString().toDouble()

        }

        if (!binding.newInvoiceAddProductPageVatRateTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            product.vatRate = binding.newInvoiceAddProductPageVatRateTextInputLayout.editText?.text.toString().toInt()

        }

        if (!binding.newInvoiceAddProductPageVatAmountTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            product.vatAmount = binding.newInvoiceAddProductPageVatAmountTextInputLayout.editText?.text.toString().toDouble()

        }

        if (!binding.newInvoiceAddProductPageDiscountRateTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            product.discountRate = binding.newInvoiceAddProductPageDiscountRateTextInputLayout.editText?.text.toString().toInt()

        }

        if (!binding.newInvoiceAddProductPageDiscountAmountTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            product.discountAmount = binding.newInvoiceAddProductPageDiscountAmountTextInputLayout.editText?.text.toString().toDouble()

        }

        if (!binding.newInvoiceAddProductPageCustomTaxRateTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            //product.customTaxRate = binding.newInvoiceAddProductPageCustomTaxRateTextInputLayout.editText?.text.toString().toInt()

        }

        if (!binding.newInvoiceAddProductPageTaxAmountTextInputLayout.editText?.text.toString().isNullOrEmpty()) {
            //product.customTaxAmount = binding.newInvoiceAddProductPageTaxAmountTextInputLayout.editText?.text.toString().toDouble()

        }

        if (!binding.newInvoiceAddProductPageProductTotalServiceAmountTextView.text.toString().isNullOrEmpty()) {
            //product.rawAmount = binding.newInvoiceAddProductPageProductTotalServiceAmountTextView.text.toString().toDouble()

        }

        if (!binding.newInvoiceAddProductPageProductTotalVatAmountTextView.text.toString().isNullOrEmpty()) {
            //product.taxAmount = binding.newInvoiceAddProductPageProductTotalVatAmountTextView.text.toString().toDouble()

        }

        if (!binding.newInvoiceAddProductPageProductTotalGrandTotalAmountTextView.text.toString().isNullOrEmpty()) {
            //product.grandTotal = binding.newInvoiceAddProductPageProductTotalGrandTotalAmountTextView.text.toString().toDouble()

        }

        //product.customTaxName = binding.newInvoiceAddProductPageAddCustomTaxTextInputLayout.editText?.text.toString()




        val currentList = DataHolder.productList.value?.toMutableList() ?: mutableListOf()
        currentList.add(product)
        DataHolder.productList.value = currentList

        finish()
    }




    fun onClick() {

        binding.newInvoiceAddProductSaveButton.setOnClickListener() {

            save()

        }

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            finish()

        }

        findViewById<TextView>(R.id.toolBarRightIcon).setOnClickListener() {
            save()

        }

    }


    private fun calculate(quantity: TextInputLayout, price: TextInputLayout) {

        if (!quantity.editText?.text.isNullOrEmpty() && !price.editText?.text.isNullOrEmpty()) {

            val total = quantity.editText?.text.toString().toInt() * price.editText?.text.toString().toFloat()
            val vat = total * 0.18

            binding.newInvoiceAddProductPageProductTotalServiceAmountTextView.setText(total.toString())
            binding.newInvoiceAddProductPageProductTotalVatAmountTextView.setText(vat.toString())
            binding.newInvoiceAddProductPageProductTotalGrandTotalAmountTextView.setText((total + vat).toString())

        }
    }



    fun init() {

        binding.newInvoiceAddProductPageQuantityTextInputLayout.editText?.doAfterTextChanged {
            calculate(binding.newInvoiceAddProductPageQuantityTextInputLayout,binding.newInvoiceAddProductPageUnitPriceTextInputLayout )
        }

        binding.newInvoiceAddProductPageUnitPriceTextInputLayout.editText?.doAfterTextChanged {
            calculate(binding.newInvoiceAddProductPageQuantityTextInputLayout,binding.newInvoiceAddProductPageUnitPriceTextInputLayout)
        }

        var units = arrayListOf("Gün", "Ay", "Yıl", "Saat", "Dakika", "Saniye", "Adet", "Paket", "Kutu", "mg" , "g", "kg", "lt", "ton", "Net Ton", "Gross Ton", "mm", "cm", "m",
            "km", "ml", "mm3", "cm2", "m2", "m3", "kJ", "cl", "KARAT", "KWH", "MWH", "Ton baş. taşıma kap.", "Brüt kalori", "1000 lt", "Saf alkol lt", "kg.m2", "Hücret adet", "Çift", "1000 m3",
            "Set", "1000 adet", "SCM", "NCM", "mmBTU", "CM³", "Düzine", "dm2", "dm", "ha", "Metretül (LM)")

        val unitsAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.select_dialog_item, units)
        binding.newInvoiceAddProductPageUnitAutoCompleteTextView.setAdapter(unitsAdapter)
        binding.newInvoiceAddProductPageUnitAutoCompleteTextView.setText("Adet",false)


        var units2 = arrayListOf("BANKA MUAMELELERİ VER.", "KKDF KESİNTİ", "ÖTV 1. LİSTE", "ÖTV 2. LİSTE", "ÖTV 3. LİSTE", "ÖTV 4. LİSTE", "ÖTV 3A LİSTE" ,"ÖTV 3B LİSTE" ,"ÖTV 3C LİSTE" ,
            "DAMGA V" ,"5035SKDAMGAV" ,"Ö.İLETİŞİM V" ,"5035ÖZİLETV." ,"KDV TEVKİFAT" ,"4961BANKASMV" ,"BORSA TES.ÜC." ,"ENERJİ FONU" ,"ELK.HAVAGAZ.TÜK.VER." ,"TRT PAYI" ,"ELK.TÜK.VER." ,
            "TK KULLANIM" ,"TK RUHSAT" ,"ÇEV. TEM .VER." ,"GV. STOPAJI" ,"KV. STOPAJI" ,"MERA FONU" ,"ÖTV 1. LİSTE TEVKİFAT" ,"BEL.ÖD.HAL RÜSUM" ,"KONAKLAMA VERGİSİ")


        val unitsAdapter2: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.select_dialog_item, units2)
        binding.newInvoiceAddProductPageAddTaxAutoCompleteTextView.setAdapter(unitsAdapter2)

    }
}







