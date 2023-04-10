package com.example.e_arsivportal.views

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biochakraastralterapi.adapters.IssuedToMeAdapter
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityIncomingInvoicesBinding
import com.example.e_arsivportal.viewmodels.IssuedToMeViewModel
import java.util.Calendar


class IncomingInvoicesActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityIncomingInvoicesBinding
    private val binding get() = _binding!!

    private lateinit var viewModel: IssuedToMeViewModel
    private lateinit var context: Context

/*
    val deleteButtonListener = object: CustomersAdapter.CustomViewHolderListener {


        override fun onCustomItemClicked(id: Int) {
            viewModel.deleteCustomer(id,context)

        }
    }

 */



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIncomingInvoicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        viewModel = ViewModelProvider(this).get(IssuedToMeViewModel::class.java)


        viewModel.getAllIssuedToMe(this)

        observeLiveData()



        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.issuedToMePageStartDateTextInputLayout?.editText?.setOnClickListener() {


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year , month , day ->

                binding.issuedToMePageStartDateTextInputLayout.editText?.setText("$day/$month/$year")
            }, year,month,day).show()

        }

        binding.issuedToMePageEndDateTextInputLayout?.editText?.setOnClickListener() {


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year , month , day ->

                binding.issuedToMePageEndDateTextInputLayout.editText?.setText("$day/$month/$year")
            }, year,month,day).show()

        }


        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).text = ""
        findViewById<TextView>(R.id.toolBarTittle).text = "Gelen Faturalar"

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            onBackPressedDispatcher.onBackPressed()
        }


    }

    fun observeLiveData() {

        viewModel.liveData.observe(this, Observer { invoiceList ->

            invoiceList.let {

                _binding?.issuedToMePageRecyclerView?.adapter =
                    this?.let { IssuedToMeAdapter(invoiceList, it) }
                    //this?.let { IssuedToMeAdapter(invoiceList, it, deleteButtonListener) }

                _binding?.issuedToMePageRecyclerView?.layoutManager = LinearLayoutManager(this)

            }
        }
        )
    }

    fun goToAddDataActivity() {
        val intent = Intent(this, AddCustomerActivity::class.java)
        startActivity(intent)
    }
}