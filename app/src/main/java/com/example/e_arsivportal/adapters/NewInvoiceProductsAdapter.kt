package com.example.biochakraastralterapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_arsivportal.databinding.NewInvoiceProductItemBinding
import com.example.e_arsivportal.models.NewInvoiceProductModel
import javax.inject.Inject

class NewInvoiceProductsAdapter @Inject constructor(
) : RecyclerView.Adapter<NewInvoiceProductsAdapter.ViewHolder>() {


    private var onItemClickListener : ((Int) -> Unit)? = null

    class ViewHolder(val binding: NewInvoiceProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<NewInvoiceProductModel>() {
        override fun areItemsTheSame(oldItem: NewInvoiceProductModel, newItem: NewInvoiceProductModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewInvoiceProductModel, newItem: NewInvoiceProductModel): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var productList: MutableList<NewInvoiceProductModel>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewInvoiceProductsAdapter.ViewHolder {
        val binding = NewInvoiceProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    fun setOnItemClickListener(listener : (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.newInvoiceProductItemName.text = productList[position].name
        holder.binding.newInvoiceProductQuantityAndPrice .text = productList[position].quantity.toString() + " X " + productList[position].unitPrice.toString() + " TRY"
        holder.binding.newInvoiceProductTotalPrice .text = (productList[position].quantity.toDouble() * productList[position].unitPrice.toDouble()).toString() + " TRY"

        holder.binding.newInvoiceProductCancelButton.setOnClickListener() {

            onItemClickListener?.let {

                it(position)
            }
        }
    }

    override fun getItemCount() = productList.size

    }




