package com.example.biochakraastralterapi.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.CustomerItemBinding
import com.example.e_arsivportal.databinding.IncomingInvoiceItemBinding
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.models.InvoiceModel

import com.example.e_arsivportal.views.AddCustomerActivity

class IssuedToMeAdapter(private val invoiceList: List<InvoiceModel>, private val context: Context) :
    RecyclerView.Adapter<IssuedToMeAdapter.ViewHolder>() {

    interface CustomViewHolderListener{
        fun onCustomItemClicked(id : Int)
    }

    class ViewHolder(val binding: IncomingInvoiceItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = IncomingInvoiceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.incomingInvoiceItemCompanyName.text = invoiceList[position].saticiUnvanAdSoyad
        holder.binding.incomingInvoiceItemDocumentNumber.text = invoiceList[position].belgeNumarasi


        /*
        holder.binding.customerItemDeleteButton.setOnClickListener() {

            deleteButtonListener.onCustomItemClicked(customerList[position].id)

            customerList.removeAt(position)

            notifyDataSetChanged()

        }

         */

    }


    override fun getItemCount() = invoiceList.size

}
