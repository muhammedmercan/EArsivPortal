package com.example.e_arsivportal.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biochakraastralterapi.adapters.ProductsAdapter
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityProductsBinding
import com.example.e_arsivportal.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding

    private lateinit var viewModel: ProductsViewModel
    private lateinit var context: Context


    val deleteButtonListener = object: ProductsAdapter.CustomViewHolderListener {


        override fun onCustomItemClicked(id: Int) {
            viewModel.deleteProduct(id)

        }

    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getDataFromRoom()
        observeLiveData()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        context = this

        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)


        viewModel.getDataFromRoom()

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


        binding.productsPageAddButton.setOnClickListener() {

            goToAddDataActivity()
        }

    }

    fun observeLiveData() {

        viewModel.liveData.observe(this, Observer { productList ->

            productList.let {

                binding?.productsPageRecyclerview?.adapter =
                    this?.let { ProductsAdapter(productList, it, deleteButtonListener) }

                binding?.productsPageRecyclerview?.layoutManager = LinearLayoutManager(this)


            }

        }
        )
    }

    fun goToAddDataActivity() {
        val intent = Intent(this, AddProductActivity::class.java)
        startActivity(intent)
    }
}