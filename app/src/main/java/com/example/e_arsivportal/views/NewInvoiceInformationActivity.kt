package com.example.e_arsivportal.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.e_arsivportal.R
import com.example.e_arsivportal.databinding.ActivityNewInvoiceInformationBinding
import com.example.e_arsivportal.models.InvoiceModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*
@AndroidEntryPoint
class NewInvoiceInformationActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityNewInvoiceInformationBinding
    private val binding get() = _binding!!

    private var invoiceModel = InvoiceModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewInvoiceInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        getData()

        onClick()



    }

    fun onClick() {




        findViewById<TextView>(R.id.toolBarLeftIcon).setOnClickListener() {
            finish()
        }

        findViewById<TextView>(R.id.toolBarRightIcon).setOnClickListener() {

            saveData()

            val intent = Intent(this, NewInvoiceProductsActivity::class.java)
            intent.putExtra("invoiceModel",invoiceModel)
            startActivity(intent)
        }


        _binding.newInvoiceInformationPageNextButton.setOnClickListener() {

            saveData()

            val intent = Intent(this, NewInvoiceProductsActivity::class.java)
            intent.putExtra("invoiceModel",invoiceModel)
            startActivity(intent)
        }}

    fun saveData() {

        invoiceModel.tarih = binding.newInvoiceInformationPageDateOfIssueTextInputLayout.editText?.text.toString()
        invoiceModel.saat = binding.newInvoiceInformationPageEditTimeTextInputLayout.editText?.text.toString()
        invoiceModel.paraBirimi = binding.newInvoiceInformationPageDocumentCurrencyTextInputLayout.editText?.text.toString()
        invoiceModel.faturaTipi = binding.newInvoiceInformationPageInvoiceTypeTextInputLayout.editText?.text.toString()
        invoiceModel.not = binding.newInvoiceInformationPageInvoiceNoteTextInputLayout.editText?.text.toString()
        invoiceModel.irsaliyeNumarasi = binding.newInvoiceInformationPageWaybillNumberTextInputLayout.editText?.text.toString()
        invoiceModel.irsaliyeTarihi = binding.newInvoiceInformationPageWaybillDateTextInputLayout.editText?.text.toString()


    }

    fun init() {

        findViewById<TextView>(R.id.toolBarTittle).text = "Fatura Bilgileri"



        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)


        val currentDate = c.time
        val formatterDate = SimpleDateFormat("dd/MM/yyyy")
        val currentDateString = formatterDate.format(currentDate)

        val formatterHour = SimpleDateFormat("hh:mm:ss")
        val currentHourString = formatterHour.format(currentDate)


        binding.newInvoiceInformationPageDateOfIssueTextInputLayout.editText?.setText(currentDateString)
        binding.newInvoiceInformationPageEditTimeTextInputLayout.editText?.setText(currentHourString)

        binding.newInvoiceInformationPageDateOfIssueTextInputLayout?.editText?.setOnClickListener() {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->

                val startDate = String.format("%02d/%02d/%d", day, month + 1 , year)

                binding.newInvoiceInformationPageDateOfIssueTextInputLayout.editText?.setText(startDate)
            }, year,month,day).show()

        }

        binding.newInvoiceInformationPageEditTimeTextInputLayout?.editText?.setOnClickListener() {

            val dpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hour, minute ->

                val startDate = String.format("%d:%d:00", hour, minute)

                binding.newInvoiceInformationPageEditTimeTextInputLayout.editText?.setText(startDate)
            }, hour,minute,true).show()

        }

        binding.newInvoiceInformationPageWaybillDateTextInputLayout?.editText?.setOnClickListener() {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->

                    val startDate = String.format("%02d/%02d/%d", day, month + 1, year)

                    binding.newInvoiceInformationPageWaybillDateTextInputLayout.editText?.setText(startDate)
                }, year, month, day).show()
        }


        var units = arrayListOf("SATIŞ", "İADE", "TEVKİFAT", "İSTİSNA", "ÖZEL MATRAH", "İHRAÇ KAYITLI", "KONAKLAMA VERGİSİ")

        val unitsAdapter: ArrayAdapter<kotlin.String> = ArrayAdapter<kotlin.String>(this,
            android.R.layout.select_dialog_item, units)
        binding.newInvoiceInformationPageInvoiceTypeAutoCompleteTextView.setAdapter(unitsAdapter)
        binding.newInvoiceInformationPageInvoiceTypeAutoCompleteTextView.setText("SATIŞ",false)

        var units2 = arrayListOf("AFN-Afghani", "DZD-Algerian Dinar", "ARS-Argentine Peso", "AWG-Aruban Guilder", "AUD-Australian Dollar", "AZM-Azerbaijanian Manat", "BSD-Bahamian Dollar",
            "BHD-Bahraini Dinar", "THB-Baht", "PAB-Balboa", "BBD-Barbados Dollar", "BYR-Belarussian Ruble", "BZD-Belize Dollar", "BMD-Bermudian Dollar", "VEB-Bolivar", "BOB-Boliviano",
            "BGN-Bulgarian Lev", "BRL-Brazilian Real", "BND-Brunei Dollar", "BIF-Burundi Franc", "CAD-Canadian Dollar", "CVE-Cape Verde Escudo", "KYD-Cayman Islands Dollar", "GHC-Cedi",
            "XAF-CFA Franc", "XOF-CFA Franc", "XPF-CFP Franc", "CLP-Chilean Peso", "COP-Colombian Peso", "KMF-Comoro Franc", "NIO-Cordoba Oro", "CRC-Costa Rican Colon", "CUP-Cuban Peso",
            "CYP-Cyprus Pound", "CZK-Czech Koruna", "DKK-Danish Krone", "GMD-Dalasi", "MKD-Denar", "AED-Dirham", "DJF-Djibouti Franc", "STD-Dobra", "DOP-Dominican Peso", "VND-Dong",
            "AMD-Dram", "XCD-East Carribean Dollar", "EGP-Egyptian Pound", "SVC-El Salvador Colon", "ETB-Ethopian Birr", "EUR-Euro", "FKP-Falkland Islands Pound", "FJD-Fiji Dollar",
            "HUF-Forint", "CDF-Franc Congolais", "GIP-Gibraltar Pound", "XAU-Gold", "HTG-Gourde", "PYG-Guarani", "GNF-Guinea Franc", "GYD-Guyana Dollar", "HKD-HKD", "UAH-Hryvnia",
            "ISK-Iceland Krona", "INR-Indian Rupee", "IQD-Iraqi Dinar", "IRR-Iranian Rial", "JMD-Jamaican Dollar", "JOD-Jordanian Dinar", "KES-Kenyan Shilling", "PGK-Kina", "LAK-Kip",
            "EEK-Kroon", "HRK-Kuna", "KWD-Kuwaiti Dinar", "MWK-Kwacha", "ZMK-Kwacha", "AOA-Kwanza", "MMK-Kyat", "GEL-Lari", "LVL-Latvian Lats", "LBP-Lebanese Pound", "ALL-Lek", "HNL-Lempira",
            "SLL-Leone", "ROL-Leu", "LRD-Liberian Dollar", "LYD-Libyan Dinar", "SZL-Lilangeni", "LTL-Lithuanian Litas", "LSL-Loti", "MGF-Malagasy Franc", "MYR-Malaysian Ringgit",
            "MTL-Maltese Lira", "TMM-Manat", "MUR-Mauritius Rupee", "MZM-Metical", "MXN-Mexican Peso", "MDL-Moldovan Leu", "MAD-Morrocan Dirham", "NGN-Naira", "ERN-Nakfa",
            "NAD-Namibia Dollar", "NPR-Nepalese Rupee", "ANG-Netherlands Antillian Guilder", "YUM-New Dinar", "ILS-New Israeli Sheqel", "TWD-New Taiwan Dollar", "NZD-New Zealand Dollar",
            "KPW-North Korean Won", "NOK-Norwegian Krone", "BTN-Ngultrum", "PEN-Nuevo Sol", "MRU-Ouguiya", "TOP-Pa&amp;apos;anga", "PKR-Pakistan Rupee", "XPD-Palladium", "MOP-Pataca",
            "UYU-Peso Uruguayo", "PHP-Philippine Peso", "XPT-Platinum", "GBP-Pound Sterling", "BWP-Pula", "QAR-Qatari Rial", "GTQ-Quetzal", "ZAR-Rand", "OMR-Rial Omani", "KHR-Riel",
            "MVR-Rufiyaa", "RON-Rumen Leyi", "IDR-Rupiah", "RUB-Russian Ruble", "RWF-Rwanda Franc", "SAR-Saudi Riyal", "XDR-SDR", "SCR-Seychelles Rupee", "XAG-Silver", "SGD-Singapore Dollar",
            "SKK-Slovak Koruna", "SBD-Solomon Islands Dollar", "SOS-Somali Shilling", "LKR-Sri Lanka Rupee", "KGS-Som", "TJS-Somoni", "SHP-St. Helena Pound", "SDD-Sudanese Dinar",
            "SRG-Suriname Guilder", "SEK-Swedish Krona", "CHF-Swiss Franc", "SYP-Syrian Pound", "BDT-Taka", "WST-Tala", "TZS-Tanzanian Shilling", "KZT-Tenge", "SIT-Tolar",
            "TTD-Trinidad and Tobago Dollar", "MNT-Tugrik", "TND-Tunisian Dinar", "TRY-Türk Lirası", "UGX-Uganda Shilling", "USD-US Dollar", "UZS-Uzbekistan Sum", "VUV-Vatu", "KRW-Won",
            "YER-Yemeni Rial", "JPY-Yen", "CNY-Yuan Renminbi", "ZWD-Zimbabwe Dollar","PLN-Zloty")

        val unitsAdapter2: ArrayAdapter<kotlin.String> = ArrayAdapter<kotlin.String>(this, android.R.layout.select_dialog_item, units2)
        binding.newInvoiceInformationPageDocumentCurrencyAutoComplateTextView.setAdapter(unitsAdapter2)
        binding.newInvoiceInformationPageDocumentCurrencyAutoComplateTextView.setText("TRY-Türk Lirası",false)

    }

    fun getData() {

        intent = intent

        invoiceModel = intent.getSerializableExtra("invoiceModel")!! as InvoiceModel


    }
}