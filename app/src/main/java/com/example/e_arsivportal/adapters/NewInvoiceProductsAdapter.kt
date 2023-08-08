package com.example.biochakraastralterapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.NewInvoiceProductItemBinding
import com.example.e_arsivportal.models.NewInvoiceProductModel

class NewInvoiceProductsAdapter(private val productList: MutableList<NewInvoiceProductModel>, private val context: Context) :
    RecyclerView.Adapter<NewInvoiceProductsAdapter.ViewHolder>() {

    interface CustomViewHolderListener{
        fun onCustomItemClicked(id : Int)
    }

    class ViewHolder(val binding: NewInvoiceProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewInvoiceProductsAdapter.ViewHolder {
        val binding = NewInvoiceProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.newInvoiceProductItemName.text = productList[position].name
        holder.binding.newInvoiceProductQuantityAndPrice .text = productList[position].quantity.toString() + " X " + productList[position].unitPrice.toString() + " TRY"
        holder.binding.newInvoiceProductTotalPrice .text = (productList[position].quantity.toDouble() * productList[position].unitPrice.toDouble()).toString() + " TRY"

        holder.binding.newInvoiceProductCancelButton.setOnClickListener() {

            productList.removeAt(position)

            notifyDataSetChanged()

        }

    }


    override fun getItemCount() = productList.size



    }




