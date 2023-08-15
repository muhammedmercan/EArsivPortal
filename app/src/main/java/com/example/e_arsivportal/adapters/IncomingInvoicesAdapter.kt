package com.example.biochakraastralterapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.IncomingInvoiceItemBinding
import com.example.e_arsivportal.models.IncomingInvoiceModel
import javax.inject.Inject

class IncomingInvoicesAdapter @Inject constructor(
) : RecyclerView.Adapter<IncomingInvoicesAdapter.ViewHolder>() {


    class ViewHolder(val binding: IncomingInvoiceItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<IncomingInvoiceModel>() {
        override fun areItemsTheSame(oldItem: IncomingInvoiceModel, newItem: IncomingInvoiceModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: IncomingInvoiceModel, newItem: IncomingInvoiceModel): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var invoiceList: List<IncomingInvoiceModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = IncomingInvoiceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.incomingInvoiceItemCompanyName.text = invoiceList[position].saticiUnvanAdSoyad
        holder.binding.incomingInvoiceItemDocumentNumber.text = invoiceList[position].belgeNumarasi
        holder.binding.incomingInvoiceItemDate.text = invoiceList[position].belgeTarihi


    }


    override fun getItemCount() = invoiceList.size

}
