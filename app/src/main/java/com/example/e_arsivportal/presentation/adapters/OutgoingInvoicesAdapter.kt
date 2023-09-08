package com.example.biochakraastralterapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.OutgoingInvoiceItemBinding
import com.example.e_arsivportal.domain.model.OutgoingInvoiceModel
import javax.inject.Inject

class OutgoingInvoicesAdapter @Inject constructor(
) : RecyclerView.Adapter<OutgoingInvoicesAdapter.ViewHolder>() {


    private var onItemClickListener : ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener : (Int) -> Unit) {
        onItemClickListener = listener
    }


    interface CustomViewHolderListener{
        fun review(ettn : String)
        fun share(ettn: String)

        fun repeat(ettn: String)

    }


    class ViewHolder(val binding: OutgoingInvoiceItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<OutgoingInvoiceModel>() {
        override fun areItemsTheSame(oldItem: OutgoingInvoiceModel, newItem: OutgoingInvoiceModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: OutgoingInvoiceModel, newItem: OutgoingInvoiceModel): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var invoiceList: List<OutgoingInvoiceModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OutgoingInvoiceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.outgoingInvoiceItemCompanyName.text = invoiceList[position].aliciUnvanAdSoyad
        holder.binding.outgoingInvoiceItemDocumentNumber.text = invoiceList[position].belgeNumarasi
        holder.binding.outgoingInvoiceItemDate.text = invoiceList[position].belgeTarihi


        holder.itemView.setOnClickListener() {

            if(holder.binding.outgoingInvoiceItemResizableConstraintLayout.visibility == View.GONE) {

                holder.binding.outgoingInvoiceItemResizableConstraintLayout.visibility = View.VISIBLE
            }

            else {
                holder.binding.outgoingInvoiceItemResizableConstraintLayout.visibility = View.GONE
            }
        }

        holder.binding.outgoingInvoiceItemPreviewImageView.setOnClickListener() {

            println(invoiceList[position].ettn)
            //buttonsListener.review(invoiceList[position].ettn)


        }

        holder.binding.outgoingInvoiceItemShareImageView.setOnClickListener() {

            //buttonsListener.share(invoiceList[position].ettn)
        }

        holder.binding.outgoingInvoiceItemRepeatImageView.setOnClickListener() {

            //buttonsListener.repeat(invoiceList[position].ettn)
        }

    }


    override fun getItemCount() = invoiceList.size

}
