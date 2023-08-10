package com.example.e_arsivportal.views

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biochakraastralterapi.adapters.OutgoingInvoicesAdapter
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityOutgoingInvoicesBinding
import com.example.e_arsivportal.models.OutgoingInvoiceModel
import com.example.e_arsivportal.viewmodels.OutgoingInvoicesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class OutgoingInvoicesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutgoingInvoicesBinding

    private lateinit var viewModel: OutgoingInvoicesViewModel
    private lateinit var context: Context

    lateinit var invoiceList:List<OutgoingInvoiceModel>

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    var mission = ""




    val CustomViewHolderListener = object: OutgoingInvoicesAdapter.CustomViewHolderListener {


        override fun review(ettn: String) {

            viewModel.getHtml(context,ettn)

            mission = "preview"

        }

        override fun share(ettn: String) {

            viewModel.getHtml(context,ettn)


            mission = "share"

        }

        override fun repeat(ettn: String) {

            viewModel.getDocument(ettn)

        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutgoingInvoicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerLauncher()



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(binding.root, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission"){
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }.show()
            } else {
                println("2222")
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }}

        context = this

        viewModel = ViewModelProvider(this).get(OutgoingInvoicesViewModel::class.java)



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


        binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(lastWeekDateString)

        binding.outgoingInvoicesPageEndDateTextInputLayout.editText?.setText(currentDateString)

        viewModel.getOutgoingInvoices(binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.text.toString(),
            binding.outgoingInvoicesPageEndDateTextInputLayout.editText?.text.toString())

        binding.outgoingInvoicesPageStartDateTextInputLayout?.editText?.setOnClickListener() {


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year , month , day ->

                val startDate = java.lang.String.format("%02d/%02d/%d", day, month + 1 , year)


                binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(startDate)
            }, year,month,day).show()

        }

        binding.outgoingInvoicesPageEndDateTextInputLayout?.editText?.setOnClickListener() {


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year , month , day ->

                val endDate = java.lang.String.format("%02d/%02d/%d", day, month + 1 , year)


                binding.outgoingInvoicesPageEndDateTextInputLayout.editText?.setText(endDate)
            }, year,month,day).show()

        }


        binding.outgoingInvoicesPageSearchTextInputLayout.editText?.doAfterTextChanged {

            filter(binding.outgoingInvoicesPageSearchTextInputLayout.editText!!.text.toString())

        }

        binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.doAfterTextChanged {

            viewModel.getOutgoingInvoices(binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.text.toString(),
                binding.outgoingInvoicesPageEndDateTextInputLayout.editText?.text.toString())

        }


        binding.outgoingInvoicesPageEndDateTextInputLayout.editText?.doAfterTextChanged {

            viewModel.getOutgoingInvoices(binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.text.toString(),
                binding.outgoingInvoicesPageEndDateTextInputLayout.editText?.text.toString())

        }


        findViewById<TextView>(R.id.toolBarLeftIcon).text = "Geri"
        findViewById<TextView>(R.id.toolBarRightIcon).text = ""
        findViewById<TextView>(R.id.toolBarTittle).text = "Giden Faturalar"

        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            onBackPressedDispatcher.onBackPressed()
        }


    }


    fun click(view: View) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val formatter = SimpleDateFormat("dd/MM/yyyy")


        when (view.getId()) {


            R.id.outgoingInvoicesPageTodayButton -> {

                val date = c.time
                val dateString = formatter.format(date)
                binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.outgoingInvoicesPageLastSevenDaysButton-> {

                c.add(Calendar.DAY_OF_MONTH, -7)
                val date = c.time
                val dateString = formatter.format(date)
                binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.outgoingInvoicesPageLastFourteenDaysButton -> {

                c.add(Calendar.DAY_OF_MONTH, -14)
                val date = c.time
                val dateString = formatter.format(date)
                binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.outgoingInvoicesPageLastThirtyDaysButton -> {

                c.add(Calendar.DAY_OF_MONTH, -30)
                val date = c.time
                val dateString = formatter.format(date)
                binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.outgoingInvoicesPageLastSixMonthsButton -> {

                c.add(Calendar.MONTH, -6)
                val date = c.time
                val dateString = formatter.format(date)
                binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
            R.id.outgoingInvoicesPageLastThisYearButton -> {

                c.set(Calendar.DAY_OF_MONTH,1)
                c.set(Calendar.MONTH,0)
                val date = c.time
                val dateString = formatter.format(date)
                binding.outgoingInvoicesPageStartDateTextInputLayout.editText?.setText(dateString)

            }
        }
    }


    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<OutgoingInvoiceModel> = ArrayList<OutgoingInvoiceModel>()

        // running a for loop to compare elements.
        for (item in invoiceList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.aliciUnvanAdSoyad.lowercase().contains(text.lowercase(Locale.getDefault()))) {
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
            binding?.outgoingInvoicesPageRecyclerView?.adapter =

                context?.let { OutgoingInvoicesAdapter(filteredlist, it,CustomViewHolderListener) }
        }
    }



    private fun preview() {

        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)

        mission = ""

    }


    private fun share() {

        val directoryPath: String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()


        val intent = Intent(Intent.ACTION_SEND)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "text/html"


        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(directoryPath + "/name_file.html"))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

        mission = ""

    }

    fun observeLiveData() {

        viewModel.invoicesliveData.observe(this, Observer { list ->

            invoiceList = list

            invoiceList.let {

                println(list.toString())

                binding?.outgoingInvoicesPageRecyclerView?.adapter =
                    this?.let { OutgoingInvoicesAdapter(list, it,CustomViewHolderListener) }
                //this?.let { OutgoingInvoices(invoiceList, it, deleteButtonListener) }

                binding?.outgoingInvoicesPageRecyclerView?.layoutManager = LinearLayoutManager(this)

            }
        }
        )

        viewModel.htmlLiveData.observe(this, Observer { htmlString ->


            htmlString.let {

                //println(htmlString)

                val directoryPath: String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
                File(directoryPath, "name_file.html").writeText(htmlString)


                if (mission.equals("preview")) {
                    preview()
                }

                if (mission.equals("share")) {
                    share()
                }




            }
        }
        )

        viewModel.documentLiveData.observe(this, Observer {

            println(it.toString())
        })



    }

    fun goToAddDataActivity() {
        val intent = Intent(this, AddCustomerActivity::class.java)
        startActivity(intent)
    }

    private fun registerLauncher() {
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                //permission granted
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


                }
            } else {
                //permission denied
                Toast.makeText(this, "Permisson needed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}