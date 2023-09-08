package com.example.e_arsivportal.presentation.incominginvoices

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biochakraastralterapi.adapters.IncomingInvoicesAdapter
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityIncomingInvoicesBinding
import com.example.e_arsivportal.domain.model.IncomingInvoiceModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class IncomingInvoicesActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: IncomingInvoicesAdapter

    private lateinit var binding: ActivityIncomingInvoicesBinding

    private lateinit var viewModel: IncomingInvoicesViewModel
    private lateinit var context: Context

    lateinit var invoiceList: List<IncomingInvoiceModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomingInvoicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        viewModel = ViewModelProvider(this).get(IncomingInvoicesViewModel::class.java)

        binding?.incomingInvoicesPageRecyclerView?.adapter = adapter

        binding?.incomingInvoicesPageRecyclerView?.layoutManager = LinearLayoutManager(this)


        observeLiveData()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val currentDate = c.time
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val currentDateString = formatter.format(currentDate)

        c.add(Calendar.DAY_OF_MONTH, -7)

        val lastWeekDate = c.time
        val lastWeekDateString = formatter.format(lastWeekDate)


        binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(lastWeekDateString)

        binding.incomingInvoicesPageEndDateTextInputLayout.editText?.setText(currentDateString)

        viewModel.getIncomingInvoices(binding.incomingInvoicesPageStartDateTextInputLayout.editText?.text.toString(),
            binding.incomingInvoicesPageEndDateTextInputLayout.editText?.text.toString())

        binding.incomingInvoicesPageStartDateTextInputLayout?.editText?.setOnClickListener() {


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year , month , day ->

                val startDate = java.lang.String.format("%02d/%02d/%d", day, month + 1 , year)


                binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(startDate)
            }, year,month,day).show()

        }

        binding.incomingInvoicesPageEndDateTextInputLayout?.editText?.setOnClickListener() {


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year , month , day ->

                val endDate = java.lang.String.format("%02d/%02d/%d", day, month + 1 , year)


                binding.incomingInvoicesPageEndDateTextInputLayout.editText?.setText(endDate)
            }, year,month,day).show()

        }


        binding.incomingInvoicesPageSearchTextInputLayout.editText?.doAfterTextChanged {

            filter(binding.incomingInvoicesPageSearchTextInputLayout.editText!!.text.toString())

        }

        binding.incomingInvoicesPageStartDateTextInputLayout.editText?.doAfterTextChanged {

            viewModel.getIncomingInvoices(binding.incomingInvoicesPageStartDateTextInputLayout.editText?.text.toString(),
                binding.incomingInvoicesPageEndDateTextInputLayout.editText?.text.toString())

        }


        binding.incomingInvoicesPageEndDateTextInputLayout.editText?.doAfterTextChanged {

            viewModel.getIncomingInvoices(binding.incomingInvoicesPageStartDateTextInputLayout.editText?.text.toString(),
                binding.incomingInvoicesPageEndDateTextInputLayout.editText?.text.toString())

        }


        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).text = ""
        findViewById<TextView>(R.id.toolBarTittle).text = "Gelen Faturalar"

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun click(view: View) {

        val c = Calendar.getInstance()

        val formatter = SimpleDateFormat("dd/MM/yyyy")

        when (view.getId()) {
            R.id.incomingInvoicesPageTodayButton -> {

                val date = c.time
                val dateString = formatter.format(date)
                binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.incomingInvoicesPageLastSevenDaysButton-> {

                c.add(Calendar.DAY_OF_MONTH, -7)
                val date = c.time
                val dateString = formatter.format(date)
                binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.incomingInvoicesPageLastFourteenDaysButton -> {

                c.add(Calendar.DAY_OF_MONTH, -14)
                val date = c.time
                val dateString = formatter.format(date)
                binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.incomingInvoicesPageLastThirtyDaysButton -> {

                c.add(Calendar.DAY_OF_MONTH, -30)
                val date = c.time
                val dateString = formatter.format(date)
                binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.incomingInvoicesPageLastSixMonthsButton -> {

                c.add(Calendar.MONTH, -6)
                val date = c.time
                val dateString = formatter.format(date)
                binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.incomingInvoicesPageLastThisYearButton -> {

                c.set(Calendar.DAY_OF_MONTH,1)
                c.set(Calendar.MONTH,0)
                val date = c.time
                val dateString = formatter.format(date)
                binding.incomingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
        }
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<IncomingInvoiceModel> = ArrayList<IncomingInvoiceModel>()

        // running a for loop to compare elements.
        for (item in invoiceList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.saticiUnvanAdSoyad.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }

            if (item.belgeNumarasi.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            //Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            binding?.incomingInvoicesPageRecyclerView?.adapter = adapter
            adapter.invoiceList = filteredlist
        }
    }

    fun observeLiveData() {

        viewModel.loading.observe(this, Observer {

        })

        viewModel.liveData.observe(this, Observer { list ->

            invoiceList = list

            invoiceList.let {

            adapter.invoiceList = invoiceList

            }
        }
        )
    }
}