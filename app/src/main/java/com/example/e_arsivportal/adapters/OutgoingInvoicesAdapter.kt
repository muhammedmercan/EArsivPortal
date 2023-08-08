package com.example.biochakraastralterapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.OutgoingInvoiceItemBinding
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.models.OutgoingInvoiceModel

class OutgoingInvoicesAdapter(private val invoiceList: List<OutgoingInvoiceModel>, private val context: Context,private val buttonsListener: OutgoingInvoicesAdapter.CustomViewHolderListener
) :
    RecyclerView.Adapter<OutgoingInvoicesAdapter.ViewHolder>() {

    interface CustomViewHolderListener{
        fun review(ettn : String)
        fun share(ettn: String)

        fun repeat(ettn: String)

    }


    class ViewHolder(val binding: OutgoingInvoiceItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

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
            buttonsListener.review(invoiceList[position].ettn)


        }

        holder.binding.outgoingInvoiceItemShareImageView.setOnClickListener() {

            buttonsListener.share(invoiceList[position].ettn)
        }

        holder.binding.outgoingInvoiceItemRepeatImageView.setOnClickListener() {

            buttonsListener.repeat(invoiceList[position].ettn)
        }


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
